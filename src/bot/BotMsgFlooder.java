
package bot;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import http.HttpRequest;

public class BotMsgFlooder implements Runnable {
	private String token;
	private String msg;
	private List<String> chIDArray;
	private String[] pArray;

	public BotMsgFlooder(final String token, final String msg, final List<String> chIDArray, final String[] pArray) {
		this.chIDArray = new ArrayList<String>();
		this.pArray = pArray;
		this.chIDArray.addAll(chIDArray);
		this.msg = msg;
		this.token = token;
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

			// spam messages as bot, this will only work if a thread is running the
			// BotGatewayIdentifier class due to the fact that discord bots cannot send
			// messages if they are not identified in the gateway and sending connection
			// messages etc.
			for (int i = 0; i < this.chIDArray.size() && !interrupted; ++i) {
				if (!Thread.interrupted()) {
					final Random rand = new Random();
					final String randProxy = this.pArray[rand.nextInt(this.pArray.length)];
					final String[] proxySplit = randProxy.split(":");
					System.setProperty("http.proxyHost", proxySplit[0]);
					System.setProperty("http.proxyPort", proxySplit[1]);
					System.out.println("proxy property set");
					try {
						System.out.println("sending bot msg");
						final String channel = this.chIDArray.get(i);
						final HttpRequest rq = new HttpRequest(
								"https://discord.com/api/v8/channels/" + channel + "/messages", "utf-8", "POST");
						rq.addHeader("Content-Type", "application/json");
						rq.addHeader("Authorization", "Bot " + this.token);
						rq.setPostData("{\"content\":\"" + this.msg + "\"}");
						final String res = rq.finish();
						System.out.println(res);
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
