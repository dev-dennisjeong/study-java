package apiTest;

import java.util.HashMap;
import java.util.Random;

import org.json.simple.JSONObject;

import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;

public class MessageTest {
	public static void main(String[] args) {
		String api_key = "NCSF8LRG4LDF323M";
		String api_secret = "UGS24OKHX6YCPEFTFUK7WBZFEFD35UCD";
		Message coolsms = new Message(api_key, api_secret);

		String inputPassword = "";

		Random r = new Random();
		for (int i = 0; i < 6; i++) {
			inputPassword += r.nextInt(10);
		}

		// 4 params(to, from, type, text) are mandatory. must be filled
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("to", "01041998127");
		params.put("from", "01041998127");
		params.put("type", "SMS");
		params.put("text", inputPassword);
		params.put("app_version", "test app 1.2"); // application name and version

		try {
			JSONObject obj = (JSONObject) coolsms.send(params);
			System.out.println(obj.toString());
		} catch (CoolsmsException e) {
			System.out.println(e.getMessage());
			System.out.println(e.getCode());
		}
	}
}
