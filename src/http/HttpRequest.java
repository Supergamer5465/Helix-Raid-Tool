
package http;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

public class HttpRequest {
	private HttpURLConnection con;
	private String postData;
	private String charset;

	public HttpRequest(final String requestURL, final String charset, final String requestMethod,
			final Map<String, String> headers, final String postData) throws IOException {
		this.charset = charset;
		this.postData = postData;
		final URL url = new URL(requestURL);
		(this.con = (HttpURLConnection) url.openConnection()).setConnectTimeout(15000);
		this.con.setReadTimeout(60000);
		this.con.setUseCaches(false);
		this.con.setDoOutput(true);
		this.con.setDoInput(true);
		this.con.setRequestMethod(requestMethod);
		if (headers != null && headers.size() > 0) {
			for (final String key : headers.keySet()) {
				final String value = headers.get(key);
				this.con.setRequestProperty(key, value);
			}
		}
	}

	public HttpRequest(final String requestURL, final String charset, final String requestMethod,
			final Map<String, String> headers) throws IOException {
		this(requestURL, charset, requestMethod, headers, null);
	}

	public HttpRequest(final String requestURL, final String charset, final String requestMethod) throws IOException {
		this(requestURL, charset, requestMethod, null);
	}

	public void addHeader(final String key, final String value) {
		this.con.setRequestProperty(key, value);
	}

	public void setPostData(final String postData) {
		this.postData = postData;
	}

	private byte[] getParamsByte() {
		byte[] result = null;
		try {
			result = this.postData.toString().getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return result;
	}

	public String finish() throws IOException {
		if (this.con.getRequestProperty("User-Agent") == null) {
			this.con.setRequestProperty("User-Agent", "DiscordBot (https://www.google.com, 1.0)");
		}

		String response = "";
		if (this.postData != null) {
			final byte[] postDataBytes = this.getParamsByte();
			this.con.getOutputStream().write(postDataBytes);
		}
		final int status = this.con.getResponseCode();
		System.out.println("HTTP status code: " + status);
		if (status == 200 || status == 204 || status == 201) {
			final ByteArrayOutputStream result = new ByteArrayOutputStream();
			final byte[] buffer = new byte[1024];
			int length;
			while ((length = this.con.getInputStream().read(buffer)) != -1) {
				result.write(buffer, 0, length);
			}
			response = result.toString(this.charset);
			this.con.disconnect();
		} else {
			final ByteArrayOutputStream result = new ByteArrayOutputStream();
			final byte[] buffer = new byte[1024];
			int length;
			while ((length = this.con.getErrorStream().read(buffer)) != -1) {
				result.write(buffer, 0, length);
			}
			response = result.toString(this.charset);
			this.con.disconnect();
			System.out.println("HTTP Request Error:\n" + response);
		}
		return response;
	}

	public int statusCode() {
		int rc = 0;
		try {
			rc = this.con.getResponseCode();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return rc;
	}
}
