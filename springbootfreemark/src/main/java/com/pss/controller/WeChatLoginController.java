package com.pss.controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.pss.enums.HttpRequestType;
import com.pss.exception.TDException;
import com.pss.property.WeChatProperties;
import com.pss.utils.WeChatAuthUtil;

import net.sf.json.JSONObject;

/**
 * This controller is used to WeChat Login. 
 * 
 * @author songsong.peng
 */
@RestController
@RequestMapping(value="/wechat")
public class WeChatLoginController {
	
	private static final Logger LOGGER  = LoggerFactory.getLogger(WeChatLoginController.class);
	
	private static final String APPID = "APPID";
	
	private static final String APPSECRET = "APPSECRET";
	
	private static final String ACCESS_TOKEN = "access_token";
	
	private static final String EXPIRES_IN = "expires_in";
	
	private static final String ERROR_CODE = "errcode";
	
	private static final String ERROR_MSG = "errmsg";
	
	private static final String REDIRECT_URI = "REDIRECT_URI";
	
	// scope type
	private static final String SNSAPI_USERINFO = "snsapi_userinfo";
	
	private static final String SCOPE = "SCOPE";
	
	private static final String CODE = "code";
	
	private static final String REFRESH_TOKEN = "refresh_token";
	
	private static final String OPEN_ID = "openid";
	
	@Autowired
	private WeChatProperties weChatProperties;
	
	@Value("${WeChat.appId}")
	private String appId;
	
	@Value("${WeChat.appSecret}")
	private String appSecret;
	
	
	@GetMapping(value="redirect")
	public void testLogin(HttpServletResponse response) throws IOException {
		LOGGER.info("========Send Redirect to get token========");
		String url = "http://localhost:8085/wechat/getToken";
		response.sendRedirect(url);
	}
	
	/**
	 * This mehod is used to user grant to get code then send redirect.
	 * 
	 * @param request the instance of HttpServletRequest
	 * @param response the instance of HttpServletResponse
	 * @throws IOException 
	 */
	@GetMapping(value="/login")
	public void LoginWeChat(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 第一步：用户同意授权，获取code
		response.sendRedirect(prepareOfficialAuthUrl());
	}
	
	@GetMapping(value="/authCallBack")
	public void authCallBack(HttpServletRequest request, HttpResponse response) throws Exception {
		//第二步：通过code换取网页授权access_token
		String officialUrl = this.prepareAuthTokenUrl(request.getParameter(CODE));
		JSONObject accessTokenjsonObject = WeChatAuthUtil.sendHttpRequest(officialUrl, HttpRequestType.GET_TYPE.name());
		Map<String, String> tokenResultMap = JSONObject.fromObject(accessTokenjsonObject);
		if(tokenResultMap.containsKey(ERROR_CODE)) {
			LOGGER.error("Get auth token occurs some errors: {}", new TDException(tokenResultMap.get(ERROR_MSG)));
		} else {
			//第三步：刷新access_token（如果需要）
			String refreshTokenUrl = this.prepareRefreshTokenUrl(tokenResultMap.get(REFRESH_TOKEN));
			JSONObject refreshJsonObject = WeChatAuthUtil.sendHttpRequest(refreshTokenUrl, HttpRequestType.GET_TYPE.name());
			Map<String, String> refreshTokenResultMap = JSONObject.fromObject(refreshJsonObject);
			if(refreshTokenResultMap.containsKey(ERROR_CODE)) {
				LOGGER.error("Get refresh auth token occurs some errors: {}", new TDException(refreshTokenResultMap.get(ERROR_MSG)));
			} else {
				//第四步：拉取用户信息(需scope为 snsapi_userinfo)
				String userInfoUrl = this.prepareUserInforUrl(refreshTokenResultMap.get(REFRESH_TOKEN), refreshTokenResultMap.get(OPEN_ID));
				JSONObject userInforJsonObject = WeChatAuthUtil.sendHttpRequest(userInfoUrl, HttpRequestType.GET_TYPE.name());
				LOGGER.info("user information is {}", userInforJsonObject);
			}
		}
	}
	
	
	/**
	 * This method is used to get auth information from WeChat.
	 * 
	 * @return map
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	@GetMapping(value="/getToken")
	public Map<String, String> weChatLogin() throws ClientProtocolException, IOException {
		Map<String, String> map = new HashMap<String, String>();
		LOGGER.info("========Start to get WeChat token========");
		JSONObject jsonObject = WeChatAuthUtil.sendHttpRequest(this.prepareTokenUrl(), HttpRequestType.GET_TYPE.name());
		Map<String, String> resultMap = JSONObject.fromObject(jsonObject);
		if(resultMap.containsKey(ERROR_CODE)) {
			map.put(ERROR_MSG, jsonObject.getString(ERROR_MSG));
		} else {
			map.put("access_token", jsonObject.getString(ACCESS_TOKEN));
			map.put("expires_in", jsonObject.getString(EXPIRES_IN));
		}
		
		LOGGER.info("========Get WeChat token End========");
		return map;
	}
	
	/**
	 * This method is used to prepare correct token url
	 * 
	 * @return the string token url
	 */
	private String prepareTokenUrl() {
		String rawTokenUrl = weChatProperties.getTokenUrl();
		return rawTokenUrl.replace(APPID, this.appId).replace(APPSECRET, this.appSecret);
	}
	
	/**
	 * This method is used to prepare correct auth url
	 * 
	 * @return the string auth url
	 */
	private String prepareOfficialAuthUrl() {
		String rawAuthUrl = weChatProperties.getAuthUrl();
		String callBackUrl = weChatProperties.getCallBackUrl();
		return rawAuthUrl.replace(APPID, this.appId).replace(REDIRECT_URI, URLEncoder.encode(callBackUrl)).replace(SCOPE, SNSAPI_USERINFO);
	}
	
	/**
	 * This method is used to prepare correct auth token url
	 * 
	 * @param the string code
	 * @return the string auth token url
	 */
	private String prepareAuthTokenUrl(String code) {
		String rawAuthTokenUrl = weChatProperties.getAuthTokenUrl();
		return rawAuthTokenUrl.replace(APPID, this.appId).replace(APPSECRET, this.appSecret).replace(CODE, code);
	}
	
	/**
	 * 
	 * This method is used to prepare correct Refresh Token Url
	 * 
	 * @param refreshToken the refresh access token code
	 * @return the string refresh token url
	 */
	private String prepareRefreshTokenUrl(String refreshToken) {
		String rawRefreshTokenUrl = weChatProperties.getRefreshTokenUrl();
		return rawRefreshTokenUrl.replace(APPID, this.appId).replace("REFRESH_TOKEN", refreshToken);
	}
	
	/**
	 *  This method is used to prepare correct Refresh Token Url
	 * 
	 * @param accessToken the string access token code
	 * @param openId the string open id
	 * @return the string url of get user information
	 */
	private String prepareUserInforUrl(String accessToken, String openId) {
		String rawUserInforUrl = weChatProperties.getUserInforUrl();
		return rawUserInforUrl.replace("ACCESS_TOKEN", accessToken).replace("OPENID", openId);
	}
}
