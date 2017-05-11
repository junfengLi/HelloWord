package com.web.wechat.util;


public enum WechatTypeEnum {
	D_W("D_W", "未认证订阅号"),
	D_R("D_R", "认证订阅号"),
	F_W("F_W", "未认证服务号"),
	F_R("F_R", "认证服务号"),;
	
	private final String key;
	private final String desc;
	WechatTypeEnum(String key, String desc){
		this.key = key;
		this.desc = desc;
	}
	
	public String getKey() {
		return key;
	}

	public String getDesc() {
		return desc;
	}
	

	public static String getDescByKey(String Key) {
		for (WechatTypeEnum type : WechatTypeEnum.values()) {
			if (type.getKey().equals(Key))
				return type.getDesc();
		}
		return "";
	}

}
