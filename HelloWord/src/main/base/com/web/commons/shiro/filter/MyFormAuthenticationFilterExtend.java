package com.web.commons.shiro.filter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;

import com.web.commons.shiro.AuthService;
import com.web.commons.util.IpUtils;
import com.web.manage.pojo.User;

/**
 * 认证过滤器，集成了FormAuthenticationFilter，处理是否验证码正常
 */
public class MyFormAuthenticationFilterExtend extends
		MyFormAuthenticationFilter {
	/**用户的业务类**/  
    private AuthService authService;  
      
    public AuthService getAuthService() {  
        return authService;  
    }  
  
    public void setAuthService(AuthService authService) {  
        this.authService = authService;  
    }  
    
	@Override
	protected boolean onLoginSuccess(AuthenticationToken token,
			Subject subject, ServletRequest request, ServletResponse response)
			throws Exception {
		boolean res = super.onLoginSuccess(token, subject, request, response);
		if (!res) {
			HttpServletRequest requesth = (HttpServletRequest) request;
			User user = authService.getUserByUserName(getUsername(request));
			user.setLoginip(IpUtils.getRemoteIp(requesth));
			user.setLogintime(System.currentTimeMillis());
			user.setLoginplace(IpUtils.getAddresses(IpUtils.getRemoteIp(requesth)));
			// 登录登出日志
			authService.save(user);
			// 在线用户

		}
		return res;
	}
}
