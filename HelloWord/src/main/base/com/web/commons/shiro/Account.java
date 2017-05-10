package com.web.commons.shiro;

import java.io.Serializable;

public class Account implements Serializable{

	private static final long serialVersionUID = -5201864323442770141L;
	
	private String realName;
	private String loginName;
	private String password;
	
	private String rememberName;

	
	public Account() {
	}
	
	public Account(String loginName,String password,String realName) {
		this.loginName = loginName;
		this.password = password;
		this.realName = realName;
	}
	
	public Account(String loginName,String password,String realName,String rememberName) {
		this.loginName = loginName;
		this.password = password;
		this.realName = realName;
		this.rememberName = rememberName;
	}
	
	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getRememberName() {
		return rememberName;
	}

	public void setRememberName(String rememberName) {
		this.rememberName = rememberName;
	}

	
}
