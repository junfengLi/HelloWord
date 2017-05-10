package com.web.commons.shiro.filter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.web.filter.authc.LogoutFilter;


/**
 * 重载登出过滤器，这里扩展了日志的插入功能
 * 
 */
public class MyLogoutFilter extends LogoutFilter {

	public MyLogoutFilter() {
		super();
	}

	protected boolean preHandle(ServletRequest request, ServletResponse response)
			throws Exception {
		System.out.println("************************退出********************************");
		return super.preHandle(request, response);
	}

}
