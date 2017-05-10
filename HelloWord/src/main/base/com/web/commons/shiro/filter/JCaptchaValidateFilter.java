package com.web.commons.shiro.filter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.util.WebUtils;

import com.web.commons.shiro.code.JCaptcha;


public class JCaptchaValidateFilter extends AccessControlFilter
{
	  private boolean jcaptchaEbabled = true;

	  private String jcaptchaParam = "jcaptchaCode";

	  public String failureKeyAttribute = "shiroLoginFailure";

	  public void setJcaptchaEbabled(boolean jcaptchaEbabled) {
	    this.jcaptchaEbabled = jcaptchaEbabled;
	  }
	  public void setJcaptchaParam(String jcaptchaParam) {
	    this.jcaptchaParam = jcaptchaParam;
	  }
	  public void setFailureKeyAttribute(String failureKeyAttribute) {
	    this.failureKeyAttribute = failureKeyAttribute;
	  }

	  protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue)
	    throws Exception
	  {
	    request.setAttribute("jcaptchaEbabled", Boolean.valueOf(this.jcaptchaEbabled));

	    HttpServletRequest httpServletRequest = WebUtils.toHttp(request);

	    if ((!this.jcaptchaEbabled) || (!"post".equalsIgnoreCase(httpServletRequest.getMethod()))) {
	      return true;
	    }

	    return JCaptcha.validateResponse(httpServletRequest, httpServletRequest.getParameter(this.jcaptchaParam));
	  }

	  protected boolean onAccessDenied(ServletRequest request, ServletResponse response)
	    throws Exception
	  {
	    request.setAttribute(failureKeyAttribute, "jCaptcha.error");
	    return true;
	  }
	}