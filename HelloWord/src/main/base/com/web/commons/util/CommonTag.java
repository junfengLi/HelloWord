package com.web.commons.util;

public class CommonTag {
	
	public static String getOpenUrl(String wechatId){
		String openUrl = ConfigUtil.BASE_URL + "/wechatInterface/" + UrlBase64Utils.encode(wechatId);
		return openUrl;
	}
	
	public static String getWsiteUrl(String wechatId){
		String wsiteUrl = ConfigUtil.BASE_URL + "/wap/index/" + UrlBase64Utils.encode(wechatId);
		return wsiteUrl;
	}
	
	
}
