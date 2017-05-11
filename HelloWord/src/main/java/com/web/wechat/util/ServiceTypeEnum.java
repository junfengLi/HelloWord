package com.web.wechat.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum ServiceTypeEnum {
	
	TEXT("Text", "文本请求"),
	IMAGE("Image", "图片请求"),
	VOICE("Voice", "语音请求"),
	VIDEO("Video", "视频请求"),
	LOCATION("Location", "位置请求"),
	LINK("Link", "链接请求"),
	EVENT("Event", "按钮请求"),
	SUBSCRIBE("Subscribe", "关注请求"),
	UNSUBSCRIBE("Unsubscribe", "取消关注"),
	DEFAULT("Default", "默认回复")
	;
	
	
	private final String key;
	private final String desc;
	ServiceTypeEnum(String key, String desc){
		this.key = key;
		this.desc = desc;
	}
	
	public String getKey() {
		return key;
	}

	public String getDesc() {
		return desc;
	}
	
	/**
	 * 特殊回复消息，类型
	 * @return
	 */
	public static List<Map<String,Object>> getList(){
		List<Map<String,Object>> rows = new ArrayList<Map<String,Object>>();
		for (ServiceTypeEnum type : ServiceTypeEnum.values()) {
			Map<String, Object> row = new HashMap<String, Object>();
			if (!type.key.equals(ServiceTypeEnum.TEXT.getKey())&&!type.key.equals(ServiceTypeEnum.EVENT.getKey())) {
				row.put("key", type.key);
				row.put("desc", type.desc);
				rows.add(row);
			}
		}
		
		return rows;
	}

	public static String getDescByKey(String Key) {
		for (ServiceTypeEnum type : ServiceTypeEnum.values()) {
			if (type.getKey().equals(Key))
				return type.getDesc();
		}
		return "";
	}

}
