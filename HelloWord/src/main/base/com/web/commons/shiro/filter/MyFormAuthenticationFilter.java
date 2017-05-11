package com.web.commons.shiro.filter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;

/**
 * 认证过滤器，集成了FormAuthenticationFilter，处理记住账号
 */
public class MyFormAuthenticationFilter extends FormAuthenticationFilter {

	public static final String DEFAULT_REMEMBERNAME_PARAM = "rememberName";

	private String rememberNameParam = DEFAULT_REMEMBERNAME_PARAM;

	@Override
	protected boolean executeLogin(ServletRequest request,
			ServletResponse response) throws Exception {
		this.createToken(request, response);
		return super.executeLogin(request, response);
	}

	@Override
    protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request, 
    		ServletResponse response)
            throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        String url = this.getSuccessUrl();
        httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + url);    //页面跳转
        return false;
    }
	
	@Override
	protected boolean isAccessAllowed(ServletRequest request,
			ServletResponse response, Object mappedValue) {
		// 允许访问
		return super.isAccessAllowed(request, response, mappedValue);
	}

	@Override
	protected boolean onAccessDenied(ServletRequest request,
			ServletResponse response, Object mappedValue) throws Exception {

		if (request.getAttribute(getFailureKeyAttribute()) != null) {
			return true;
		}
		boolean flag = super.onAccessDenied(request, response, mappedValue);
//		if (flag) {
//			Session session = getSubject(request, response).getSession(false);
//			if (session == null) {
//				request.setAttribute("error", "账户已被强制退出，请重新登录");
//			} else {
//				// request.setAttribute("error", "session失效或账户已其他地方登录，请重新登录");
//			}
//		}
		return flag;
	}

	@Override
	protected MyUsernamePasswordToken createToken(ServletRequest request,
			ServletResponse response) {
		String username = getUsername(request);
		username = StringUtils.isBlank(username)?"":username;
		String password = getPassword(request);
		password = StringUtils.isBlank(password)?"":password;
		String rememberName = getRememberName(request);
		boolean rememberMe = isRememberMe(request);
		String host = getHost(request);

		MyUsernamePasswordToken token = new MyUsernamePasswordToken(username,
				password.toCharArray(), rememberMe, host, rememberName);
		return token;
	}

	public String getRememberNameParam() {
		return rememberNameParam;
	}

	public void setRememberNameParam(String rememberNameParam) {
		this.rememberNameParam = rememberNameParam;
	}

	protected String getRememberName(ServletRequest request) {
		return WebUtils.getCleanParam(request, getRememberNameParam());
	}

}
