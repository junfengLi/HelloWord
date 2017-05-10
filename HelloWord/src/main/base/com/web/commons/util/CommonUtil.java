package com.web.commons.util;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import com.web.commons.shiro.Account;

public class CommonUtil {

	public static String getLoginName(){
		Subject subject = SecurityUtils.getSubject();
		Account account  = (Account)subject.getPrincipal();
		return account.getLoginName();
	}
}
