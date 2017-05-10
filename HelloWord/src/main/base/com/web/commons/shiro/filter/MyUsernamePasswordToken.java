package com.web.commons.shiro.filter;


import org.apache.shiro.authc.UsernamePasswordToken;

public class MyUsernamePasswordToken extends UsernamePasswordToken{
	
	private static final long serialVersionUID = 3107953854704420943L;

	public MyUsernamePasswordToken(String username, char[] password,String rememberName){
		super(username,password);
		this.rememberName = rememberName;
	}
	
	public MyUsernamePasswordToken(String username, char[] password,  
            boolean rememberMe, String host,String rememberName){
		super(username,password,rememberMe,host);
		this.rememberName = rememberName;
	}
	
	private String rememberName;

	public String getRememberName() {
		return rememberName;
	}

	public void setRememberName(String rememberName) {
		this.rememberName = rememberName;
	}
	
	
}
