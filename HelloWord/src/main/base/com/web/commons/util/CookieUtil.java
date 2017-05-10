package com.web.commons.util;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieUtil {
	public static final String USERID = "userId";
	public static final String ADMINID = "adminId";
	public static final String LOGINNAME = "loginName";		//前台用户使用，用来区分附件文件夹名
	public static final String USERNAME = "userName";
	public static final String ADMINNAME = "adminName";
	
	/**
	 * 设置cookie
	 * @param response
	 * @param name  cookie名字
	 * @param value cookie值
	 * @param maxAge cookie生命周期  以秒为单位
	 */
	public static void addCookie(HttpServletResponse response,String name,String value,int maxAge){
		value = UrlBase64Utils.encode(value);
	    Cookie cookie = new Cookie(name,value);
	    cookie.setPath("/");
	    maxAge = maxAge>0?maxAge:-1;
	    cookie.setMaxAge(maxAge);
	    response.addCookie(cookie);
	}
	
	/**
	 * 
	 * @param request
	 * @param name
	 * @return
	 */
	public static String getValueByName(HttpServletRequest request,String name){
		Cookie cookie = getCookieByName(request, name);
		String value = cookie == null?"":cookie.getValue();
		value = UrlBase64Utils.decode(value);
		return value;
	}
	/**
	 * 根据名字获取cookie
	 * @param request
	 * @param name cookie名字
	 * @return
	 */
	public static Cookie getCookieByName(HttpServletRequest request,String name){
	    Map<String,Cookie> cookieMap = ReadCookieMap(request);
	    if(cookieMap.containsKey(name)){
	        Cookie cookie = (Cookie)cookieMap.get(name);
	        return cookie;
	    }else{
	        return null;
	    }   
	}
	
	/**
	 * 将cookie封装到Map里面
	 * @param request
	 * @return
	 */
	private static Map<String,Cookie> ReadCookieMap(HttpServletRequest request){  
	    Map<String,Cookie> cookieMap = new HashMap<String,Cookie>();
	    Cookie[] cookies = request.getCookies();
	    if(null!=cookies){
	        for(Cookie cookie : cookies){
	            cookieMap.put(cookie.getName(), cookie);
	        }
	    }
	    return cookieMap;
	}
	
	/**
	 * 移除cookie
	 * @param response
	 * @param name
	 */
	public static void removeCookie(HttpServletResponse response,String name){
	    Cookie cookie = new Cookie(name,"");
	    cookie.setPath("/");
	    cookie.setMaxAge(0);
	    response.addCookie(cookie);
	}
}
