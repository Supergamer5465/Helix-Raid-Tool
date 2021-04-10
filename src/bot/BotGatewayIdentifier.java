
package bot;

import java.io.IOException;
import java.util.List;

import com.neovisionaries.ws.client.WebSocket;
import com.neovisionaries.ws.client.WebSocketAdapter;
import com.neovisionaries.ws.client.WebSocketException;
import com.neovisionaries.ws.client.WebSocketFactory;
import com.neovisionaries.ws.client.WebSocketListener;

public class BotGatewayIdentifier implements Runnable {
	private int sequence;
	private static String intervalMsg;
	private long interval;
	private static String response;
	private boolean interrupted;
	private WebSocket ws;
	private String token;

	private static List<WebSocketListener> listenerList;

	public BotGatewayIdentifier(final String token) {
		this.sequence = 3;
		this.token = token;
	}

	@Override
	public void run() {
		Runnable r = new BotGatewayIdentifier(this.token);
		try {
			if (!Thread.interrupted() && !this.interrupted) {

				// add listeners
				listenerList.add((WebSocketListener) new WebSocketAdapter() {
					@Override
					public void onTextMessage(final WebSocket websocket, final String message) {
						if (message != null) {
							System.out.println(message);
							BotGatewayIdentifier.response = message;
							if (BotGatewayIdentifier.response.indexOf("\"op\":10") != -1) {
								BotGatewayIdentifier.intervalMsg = message;
							}
						}
					}
				});
				listenerList.add((WebSocketListener) new WebSocketAdapter() {
					public void onDisconnected(final WebSocket websocket) {
						Thread t = new Thread(r);
						t.start();
						Thread.currentThread().interrupt();
					}
				});

				this.ws = connect();
				final String identify = "{\r\n    \"op\": 2,\r\n    \"d\": {\r\n        \"token\": \"" + this.token
						+ "\",\r\n        \"properties\": {\r\n            \"$os\": \"linux\",\r\n            \"$browser\": \"discord\",\r\n            \"$device\": \"discord\"\r\n        }\r\n    },\r\n    \"s\": null,\r\n    \"t\": null\r\n}";
				// identify to discord api gateway
				this.ws.sendText(identify, false);
				Thread.sleep(5000L);

				// get heartbeat interval
				if (BotGatewayIdentifier.intervalMsg.indexOf("\"op\":10") != -1) {
					final int intervalPos = BotGatewayIdentifier.intervalMsg.indexOf("\"heartbeat_interval\":");
					String intervalTemp = BotGatewayIdentifier.intervalMsg.substring(intervalPos + 21);
					final int intervalEndpoint = intervalTemp.indexOf(",");
					intervalTemp = intervalTemp.substring(0, intervalEndpoint);
					System.out.println("Heartbeat Interval: " + intervalTemp + " ms");
					this.interval = Integer.parseInt(intervalTemp);
				}
			} else {
				this.interrupted = true;
			}
			// send heartbeat message once per interval
			while (!this.interrupted && !Thread.interrupted()) {
				Thread.sleep(this.interval);
				final String heartbeat = "{\"op\": 1,\"d\": " + this.sequence + "}";
				this.ws.sendText(heartbeat, false);
				++this.sequence;
			}
			this.interrupted = true;
			this.ws.disconnect();
		} catch (IOException | WebSocketException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
			this.interrupted = true;
		}
	}

	// connect to websocket using websocket client from
	// https://github.com/TakahikoKawasaki/nv-websocket-client
	private static WebSocket connect() throws IOException, WebSocketException {
		return new WebSocketFactory().setConnectionTimeout(5000)
				.createSocket("wss://gateway.discord.gg/?v=6&encoding=json").addListeners(listenerList)
				.addExtension("permessage-deflate").connect();
	}
}
