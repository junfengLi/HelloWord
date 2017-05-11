package com.web.wechat.util;


public enum UserStatusEnum {
	BLACK("9", "黑名单"),
	DELETE("8", "假删除"),
	UNACTIVE("0", "已激活"),
	ACTIVE("1", "未激活");
	
	private final String key;
	private final String desc;
	UserStatusEnum(String key, String desc){
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
		for (UserStatusEnum type : UserStatusEnum.values()) {
			if (type.getKey().equals(Key))
				return type.getDesc();
		}
		return "";
	}

}
