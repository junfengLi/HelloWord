package com.web.wechat.util;


public enum MessageTypeEnum {
	
	TEXT("Text", "文本消息"),
	IMAGE("Image", "图片消息"),
	IMG("Img", "图文消息");
	
	
	private final String key;
	private final String desc;
	MessageTypeEnum(String key, String desc){
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
		for (MessageTypeEnum type : MessageTypeEnum.values()) {
			if (type.getKey().equals(Key))
				return type.getDesc();
		}
		return "";
	}

}
