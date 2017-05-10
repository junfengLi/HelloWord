package com.web.commons.shiro.filter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;

/**
 * 认证过滤器，集成了FormAuthenticationFilter，处理是否验证码正常
 */
public class MyFormAuthenticationFilterExtend extends
		MyFormAuthenticationFilter {
	
	@Override
	protected boolean onLoginSuccess(AuthenticationToken token,
			Subject subject, ServletRequest request, ServletResponse response)
			throws Exception {
		boolean res = super.onLoginSuccess(token, subject, request, response);
		if (res == false) {
//			User user = authService.getUserByUserName(getUsername(request));

			// 登录登出日志
//			System.out.println(user.getUsername() + "登入");
			System.out.println("登入");
			// 登录日志精简版，用于账户查询 20160614bylbc

			// 在线用户

		}
		return res;
	}
}
