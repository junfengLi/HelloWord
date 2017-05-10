package com.web.commons.util;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author ：zzy
 *描述:是否
 */
public enum IsOrEnum {
	SHI("1","是"),
	FOU("0","否");
	private String key;
	private String value;
	IsOrEnum(String key,String value) {
		this.key=key;
		this.value=value;
	}
	public String getKey(){
		return this.key;
	}
	public String getValue(){
		return this.value;
	}
	/**
	 * 
	 *2015-1-26下午4:18:46
	 *user:zzy
	 *desc:key获取value
	 * @param key
	 * @return
	 */
	public static String getValuByKey(String key){
		for(IsOrEnum type:IsOrEnum.values()){
			if(type.getKey().equals(key)){
				return type.getValue();
			}
		}
		return "";
	}
	/**
	 * 
	 *2015-1-26下午4:19:19
	 *user:zzy
	 *desc:根据value获取key
	 * @param value
	 * @return
	 */
	public static String getKeyByValue(String value){
		for(IsOrEnum type:IsOrEnum.values()){
			if(type.getValue().equals(value)){
				return type.getKey();
			}
		}
		return "";
	}
}
