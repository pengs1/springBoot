package com.pss.property;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * This property class is used to store all the webchat basic information.
 * 
 * @author songsong.peng
 */
@Component
@ConfigurationProperties(prefix = "WeChat")
public class WeChatProperties {
	
	private String appId;
	
	private String appSecret;
	
	private String tokenUrl;
	
	private String authUrl;
	
	private String callBackUrl;
	
	private String authTokenUrl;
	
	private String refreshTokenUrl;
	
	private String userInforUrl;

	public String getTokenUrl() {
		return tokenUrl;
	}

	public void setTokenUrl(String tokenUrl) {
		this.tokenUrl = tokenUrl;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getAppSecret() {
		return appSecret;
	}

	public void setAppSecret(String appSecret) {
		this.appSecret = appSecret;
	}
	
	public String getAuthUrl() {
		return authUrl;
	}

	public void setAuthUrl(String authUrl) {
		this.authUrl = authUrl;
	}

	public String getCallBackUrl() {
		return callBackUrl;
	}

	public void setCallBackUrl(String callBackUrl) {
		this.callBackUrl = callBackUrl;
	}

	public String getAuthTokenUrl() {
		return authTokenUrl;
	}

	public void setAuthTokenUrl(String authTokenUrl) {
		this.authTokenUrl = authTokenUrl;
	}

	public String getRefreshTokenUrl() {
		return refreshTokenUrl;
	}

	public void setRefreshTokenUrl(String refreshTokenUrl) {
		this.refreshTokenUrl = refreshTokenUrl;
	}

	public String getUserInforUrl() {
		return userInforUrl;
	}

	public void setUserInforUrl(String userInforUrl) {
		this.userInforUrl = userInforUrl;
	}
	
}
