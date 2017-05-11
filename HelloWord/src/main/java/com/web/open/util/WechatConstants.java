package com.web.open.util;

public class WechatConstants {
	/**
	 * 接口对接token
	 */
	public static final String GLOBAL_TOKEN = "qbtest";
	/**
	 * 获取token
	 */
	public static final String WECHAT_API_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
	/**
	 * 获取短连接
	 */
	public static final String WECHAT_API_SHORT_URL = "https://api.weixin.qq.com/cgi-bin/shorturl?access_token=ACCESS_TOKEN";
	

	
	/**
	 * 上传永久图片素材返回media_id
	 */
	public static final String WECHAT_API_UPLOAD_MEDIA = "https://api.weixin.qq.com/cgi-bin/material/add_material?access_token=ACCESS_TOKEN&type=TYPE";
	/**
	 * 上传图文消息的图片素材返回url
	 */
	public static final String WECHAT_API_UPLOAD_IMG = "https://api.weixin.qq.com/cgi-bin/media/uploadimg?access_token=ACCESS_TOKEN";
	/**
	 * 获取素材总数
	 */
	public static final String WECHAT_API_MATERIAL_URL = "https://api.weixin.qq.com/cgi-bin/material/get_materialcount?access_token=ACCESS_TOKEN";
	/**
	 * 生成菜单
	 */
	public static final String WECHAT_API_MENU_CREAT_URL = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";
	/**
	 * 获取菜单
	 */
	public static final String WECHAT_API_MENU_GET_URL = "https://api.weixin.qq.com/cgi-bin/menu/get?access_token=ACCESS_TOKEN";
	/**
	 * 删除菜单
	 */
	public static final String WECHAT_API_MENU_DELETE_URL = "https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=ACCESS_TOKEN";
	
	
	public static final String WECHAT_API_SELFMENU_URL = "https://api.weixin.qq.com/cgi-bin/get_current_selfmenu_info?access_token=ACCESS_TOKEN";
	
	
	/**
	 * 客服主动发送消息
	 */
	public static final String WECHAT_API_KF_SENDMESSAGE_URL = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=ACCESS_TOKEN";
	

	
	
	

	/**
	 * 服务端消息请求类型：事件
	 */
	public static final String REQ_MSG_TYPE_EVENT = "event";
	public static final String REQ_MSG_TYPE_EVENT_SUBSCRIBE = "subscribe";
	public static final String REQ_MSG_TYPE_EVENT_UNSUBSCRIBE = "unsubscribe";
	/**
	 * 消息响应
	 */
	public static final String REQ_MSG_TYPE_EVENT_CLICK = "click";
	/**
	 * 直接跳转页面
	 */
	public static final String REQ_MSG_TYPE_EVENT_VIEW = "view";
	
	/**
	 * 服务端消息请求类型：普通消息类型
	 */
	public static final String REQ_MSG_TYPE_TEXT = "text";
	public static final String REQ_MSG_TYPE_IMAGE = "image";
	public static final String REQ_MSG_TYPE_LINK = "link";
	public static final String REQ_MSG_TYPE_VOICE = "voice";
	public static final String REQ_MSG_TYPE_VIDEO = "video";
	public static final String REQ_MSG_TYPE_LOCATION = "location";
	

}
