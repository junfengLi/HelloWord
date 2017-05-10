package com.web.commons.util;


import javax.servlet.http.HttpServletRequest;


public class ConfigUtil {
	
//	public static final String BASE_URL = getPath(); 
	public static final String BASE_PATH_INDEX = "/index";
	public static final String BASE_PATH_ADMIN_LOGIN = "/adminLogin";
	public static final String BASE_PATH_JUMP = "/index/jump/forward";
	public static final String BASE_PATH_LOGIN = "/index/login/forward";
	
	public static final String MAIL_SMTPSERVER = "smtp.163.com";
	public static final String MAIL_SMTPSERVERPORT = "25";
	public static final String MAIL_USER = "";
	public static final String MAIL_PASSWORD = "";
	public static final String MAIL_FROM = "service";
	
	public static final String MODULE_BANNER = "banner";
	public static final String MODULE_ERWEIMA = "erweima";
	public static final String MODULE_LINK = "link";
	public static final String MODULE_INFO = "info";
	
	public static final String SITE_ID = "project_site_config";
	
	public static final int PAGESIZE_DEFAULT = 10;	
	public static final int PAGESIZE_NEWS = 10;		//新闻
	public static final int PAGESIZE_HZ = 10;		//合作
	public static final int PAGESIZE_AL = 10;		//案例
	
	public static final String TYPE_CD = "A000000000";	
//	public static final String TYPE_TT = "B002002000";		//头条
	public static final String TYPE_CXBD = "B001001000";		//畅销榜单位置
	public static final String TYPE_SYDT = "B001002000";		//首页大图展示
	

	public static boolean isMobile(HttpServletRequest request){
		boolean isMoblie = false;
		String[] mobileAgents = { "android", "iphone", "mobile", "ipad"};
		if (request.getHeader("User-Agent") != null) {
			for (String mobileAgent : mobileAgents) {
				if (request.getHeader("User-Agent").toLowerCase().indexOf(mobileAgent) >= 0) {
					isMoblie = true;
					break;
				}
			}
		}
		return isMoblie;
	
	}
	
}
