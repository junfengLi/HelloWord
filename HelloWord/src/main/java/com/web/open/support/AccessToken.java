package com.web.open.support;

/**
 * 
 * ClassName:AccessToken 微信访问token
 * Function: TODO ADD FUNCTION
 *
 * @author   JohnYe
 * @version  
 * @since    
 * @Date	 2015	2015年8月27日		下午4:22:02
 *
 * @see
 */
public class AccessToken {

	private String token;
	private long expiresIn;
	
	public AccessToken(String token,long expiresIn){
		this.token = token;
		this.expiresIn = expiresIn;
	}
	
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public long getExpiresIn() {
		return expiresIn;
	}
	public void setExpiresIn(long expiresIn) {
		this.expiresIn = expiresIn;
	}
	
	
}
