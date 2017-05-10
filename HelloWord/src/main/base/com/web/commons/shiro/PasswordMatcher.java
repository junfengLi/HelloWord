package com.web.commons.shiro;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;

import com.web.commons.util.UrlBase64Utils;

public class PasswordMatcher extends SimpleCredentialsMatcher {
	 @Override   
	    public boolean doCredentialsMatch(AuthenticationToken authcToken, AuthenticationInfo info) {    
	             
		    UsernamePasswordToken token = (UsernamePasswordToken) authcToken;   
		    Object accountCredentials = getCredentials(info);  
		    String pwd =UrlBase64Utils.encode(String.valueOf(token.getPassword()));//base64加密 
		    return equals(pwd, accountCredentials);  
		    //将密码加密与系统加密后的密码校验，内容一致就返回true,不一致就返回false   
	    }  
	      
}
