package framework;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import org.json.JSONObject;
import org.testng.annotations.Test;

import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.request.GetRequest;
import com.mashape.unirest.request.HttpRequestWithBody;

public class HttpRequests {
	private static GetRequest get = null;
	private static HttpRequestWithBody post;

	//GET methods
	public static String performGetRequest(String url) {
		setUrlForGET(url);
		try {
			return get.asString().getBody();
		} catch (UnirestException e) {
			System.err.println("Unable to perform GET and return String: " + e);
			return null;
		}
	}

	public static void setUrlForGET(String url) {
		get = null;
		get = Unirest.get(url);
	}

	public static String performGetReturnString() {
		try {
			return get.asString().getBody();
		} catch (UnirestException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static JSONObject performGetReturnJSON() {
		try {
			return get.asJson().getBody().getObject();
		} catch (UnirestException e) {
			System.err.println("Unable to perform GET and return JSON: " + e);
			return null;
		}
	}

	public static void setHeaderForGET(String name, String value) {
		if (get != null)
			get.header(name, value);
		else
			System.err.println("Set GET URL before HEADERS");
	}


	//POST methods
	public static JSONObject performPostReturnJSON(String url) {
		setUrlForPOST(url);
		try {
			return  post.asJson().getBody().getObject();
		} catch (Exception e) {
			System.err.println("Post return failed for: " + url);
			System.err.println("POST failed: " + e);
			return null;
		}
	}

	public static JSONObject performPostReturnJSON() {
		try {
			JSONObject jo = post.asJson().getBody().getObject();
			System.out.println("JO: " + jo.toString());
			return jo;
		} catch (Exception e) {
			System.err.println("Post return failed for: " + post.getUrl());
			System.err.println("POST failed: " + e);
			return new JSONObject();
		}
	}

	public static void setUrlForPOST(String url) {
		post = null;
		post = Unirest.post(url);
	}

	public static void setHeaderForPost(String name, String value) {
		if (post != null)
			post.header(name, value);
		else
			System.err.println("Set POST URL before HEADERS");
	}

	public static String performPostReturnString() {
		try {
			System.out.println(post.getUrl());
			String posta = post.asString().getBody();
			System.out.println("din post: "+ posta);
			return  posta;
		} catch (Exception e) {
			System.err.println("Post return failed for: " + post.getUrl());
			System.err.println("POST failed: " + e);
			return null;
		}
	}

	public static String performGetHeader(String url) {
		setUrlForGET(url);
		try {
			return get.asString().getHeaders().toString();
		} catch (UnirestException e) {
			System.err.println("Unable to perform GET and return String: " + e);
			return null;
		}
	}

	public static void setBodyPost(String body) {
		post.body(body);

	}

	@Test
	public void socketT() throws UnknownHostException, IOException {
		Socket s= new Socket("192.168.0.28", 2000);
		System.out.println(s.getInputStream().read());

	}

}
