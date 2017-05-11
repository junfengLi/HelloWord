package com.web.open.support;

import java.io.Serializable;
/**
 * 
 * ClassName:JsApiToken jsToken对象
 * Function: TODO ADD FUNCTION
 *
 * @author   JohnYe
 * @version  
 * @since    
 * @Date	 2015	2015年9月2日		下午1:37:42
 *
 * @see
 */
public class JsApiToken implements Serializable{
	/**
	 * serialVersionUID:TODO（用一句话描述这个变量表示什么）
	 *
	 * @since 
	 */
	
	private static final long serialVersionUID = -365239227982587922L;
	private String appId;
	private String noncestr;
	private String timestamp;
	private String signature;
	private long expiresInJsapi;
	private String jsapiTicketToken;
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	public String getNoncestr() {
		return noncestr;
	}
	public void setNoncestr(String noncestr) {
		this.noncestr = noncestr;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	public String getSignature() {
		return signature;
	}
	public void setSignature(String signature) {
		this.signature = signature;
	}
	public long getExpiresInJsapi() {
		return expiresInJsapi;
	}
	public void setExpiresInJsapi(long expiresInJsapi) {
		this.expiresInJsapi = expiresInJsapi;
	}
	public String getJsapiTicketToken() {
		return jsapiTicketToken;
	}
	public void setJsapiTicketToken(String jsapiTicketToken) {
		this.jsapiTicketToken = jsapiTicketToken;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
