package com.web.wechat.util;

import org.apache.commons.lang.StringUtils;

public class KeywordUtil {

	/**
	 * 关键词转换，前台到数据库，和数据库到前台展示需要转换一遍间隔符号
	 * @param keyword
	 * @param isToDB
	 */
	public static String changeKeyword(String keyword, boolean isToDB){
		if (StringUtils.isNotBlank(keyword)) {
			if (isToDB) {
				keyword = keyword.replace("，", ",");
				keyword = keyword.replaceAll("[,]+",",");
				keyword = "&" + keyword.replace(",","&") + "&";
			} else {
				keyword = keyword.substring(1, keyword.length()-1);
				keyword = keyword.replace("&", ",");
			}
		}
		return keyword;
	}
}
