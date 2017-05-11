package com.web.commons.util;


import javax.servlet.http.HttpServletRequest;


public class ConfigUtil {
	
	public static final String BASE_URL = PropertyUtil.getInstance().getValue("sys.baseurl");; 
	public static final String BASE_PATH_INDEX = "/index";
	public static final String BASE_PATH_JUMP = "/jump";
	public static final String BASE_PATH_CONFIRM = "/confirm";
	public static final String BASE_PATH_USER_LOGIN = "/login";
	public static final String BASE_PATH_ADMIN_LOGIN = "/admin";
	public static final String BASE_PATH_WECHAT_SHOW = BASE_URL + "/wap/show";
	
	
	public static final String MAIL_SMTPSERVER = "smtp.163.com";
	public static final String MAIL_SMTPSERVERPORT = "25";
	public static final String MAIL_USER = "li15268178753@163.com";
	public static final String MAIL_PASSWORD = "l110110";
	public static final String MAIL_FROM = "微信助手";
	
	public static final String MODULE_BANNER = "banner";			//网站banner图
	public static final String MODULE_LINK = "link";				//网站友情链接图片
	public static final String MODULE_INFO = "info";				//网站新闻信息图片
	public static final String MODULE_WECHAT_HEAD = "wechatHead";	//微信头像图片
	public static final String MODULE_WECHAT_IMG = "wechatImg";		//微信图文消息图片
	public static final String MODULE_WECHAT_BANNER = "wechatBanner";		//微信图文消息图片
	public static final String MODULE_WECHAT_IMAGE = "wechatImage";		//微信图片消息图片
	
	
	public static final String SITE_ID = "project_site_config";		//网站信息id
	
	public static final int PAGESIZE_DEFAULT = 10;	
	public static final int PAGESIZE_NEWS = 10;		//新闻
	public static final int PAGESIZE_HZ = 10;		//合作
	public static final int PAGESIZE_AL = 10;		//案例
	public static final int PAGESIZE_WAP = 8;		//微网站列表
	
	public static final String TYPE_TT = "B002002000";		//头条
	public static final String TYPE_CX = "B001002000";		//促销品
	
	
	public static final String MENU_YJCD = "YJCD";		//分组菜单
	
	public static final String MENU_EVENT_CLICK = "click";		//点击事件
	public static final String MENU_EVENT_VIEW = "view";		//跳转链接
	
	

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
