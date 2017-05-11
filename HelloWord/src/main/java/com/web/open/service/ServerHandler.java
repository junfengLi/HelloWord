package com.web.open.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.web.open.menu.Menu;
import com.web.open.support.AccessToken;
import com.web.open.support.JsApiToken;

/**
 * ClassName:ServerInterface 微信服务端处理接口
 * Function: TODO ADD FUNCTION
 * @Date	 2015	2015年8月26日		下午2:46:39
 * @see
 */
public interface ServerHandler{

	/**
	 * handleRequest: (处理微信服务端请求)
	 * @param request
	 * @param openId 
	 * @return  String    
	 * @date    2015年8月26日
	 */
	public String handleRequest(HttpServletRequest request, String openId);
	
	
	/**
	 * getToken: (获取微信token)
	 * @return  AccessToken    
	 * @date    2015年8月27日
	 */
	public String getWechatUser(String openId);
	/**
	 * getOpenIdByCode: (认证模式跳转页面，通过code获取openId)
	 * @param code
	 * @return  String    
	 * @date    2015年9月7日
	 */
	public String getOpenIdByCode(String code);
	/**
	 * getJsToken: (获取jstoken)
	 * @return  JsApiToken    
	 * @date    2015年9月2日
	 */
	public JsApiToken getJsToken(String url);
	/**
	 * 
	 * eventHandler: (事件响应统一入口)
	 * @param requestMap
	 * @date    2015年8月28日
	 */
//	public String eventHandler(Map<String,String> requestMap);
	/**
	 * msgHandler: (消息统一处理入口)
	 * @param requestMap
	 * @date    2015年8月28日
	 */
	public String msgHandler(Map<String,String> requestMap);
}
