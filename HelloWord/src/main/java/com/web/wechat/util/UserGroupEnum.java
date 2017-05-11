package com.web.wechat.util;


public enum UserGroupEnum {
	V1("V1", "Vip1级会员"),
	V2("V2", "Vip2级会员");
	
	private final String key;
	private final String desc;
	UserGroupEnum(String key, String desc){
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
		for (UserGroupEnum type : UserGroupEnum.values()) {
			if (type.getKey().equals(Key))
				return type.getDesc();
		}
		return "";
	}

}
