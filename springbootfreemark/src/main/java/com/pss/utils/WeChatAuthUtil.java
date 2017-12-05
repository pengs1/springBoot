package com.pss.utils;

import java.io.IOException;
import java.net.URLEncoder;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.util.EntityUtils;

import com.pss.enums.HttpRequestType;

import net.sf.json.JSONObject;


public class WeChatAuthUtil {
	
	private static final String CHARSET_NAME = "UTF-8";
	
	public static JSONObject sendHttpRequest(String url, String type) throws ClientProtocolException, IOException {
		JSONObject jsonObject = null;
		DefaultHttpClient httpClient = new DefaultHttpClient();
		httpClient.getParams().setParameter(CoreProtocolPNames.PROTOCOL_VERSION, HttpVersion.HTTP_1_1);
		HttpRequestBase request = null;
		if(type.equals(HttpRequestType.GET_TYPE.name())) {
			request = new HttpGet(url);
		} else if(type.equals(HttpRequestType.POST_TYPE.name())) {
			request = new HttpPost(url);
		}
		HttpResponse responseObject = httpClient.execute(request);
		HttpEntity entity = responseObject.getEntity();
		if(entity != null) {
			String entityStr = EntityUtils.toString(entity, CHARSET_NAME);
			jsonObject = JSONObject.fromObject(entityStr);
		}
		request.releaseConnection();
		return jsonObject;
	}
}
