package com.cly.comm.http;

import org.junit.Test;

import com.cly.comm.client.http.HttpClient;
import com.cly.comm.client.http.HttpRequestParam;

import net.sf.json.JSONObject;

public class HttlClientTest {

	@Test
	public void testDoPost() {

		try {

			String url = "http://localhost:8080/cloud-security-server/rest/user/validate";

			HttpRequestParam rp = new HttpRequestParam();

			rp.setConnectionTimeout(5 * 1000);

			rp.setReadTimeout(5 * 1000);

			JSONObject msg=new JSONObject();
			
			msg.put("userId", "johnny.cao");
			msg.put("userPwd", "ldap123");
			rp.addParam("reqMsg", msg.toString());		 
			
			//InputStream is=HttpClient.getInputStream(url, HttpClient.REQUEST_METHOD_POST, rp);
			
			//System.out.println(new String(IOUtil.getInputStreamBytes(is)));
			
			String res = HttpClient.request(url, HttpClient.REQUEST_METHOD_POST,  rp);
			System.out.println("response:" + res);

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}

	}

}
