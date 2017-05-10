package com.web.commons.util;

import org.apache.log4j.Logger;

public class LogUtil {
	public static final String LEVEL_INFO = "info";
	public static final String LEVEL_DEBUG = "debug";
	public static final String LEVEL_ERROR = "error";
	
	public static void log(String info){
		log(info, LEVEL_INFO);
	}
	public static void log(String info,String level){
		Logger logger = Logger.getLogger(LogUtil.class);
		if (LEVEL_DEBUG.equals(level)) {
			logger.debug("【" + info + "】");
		} else if (LEVEL_ERROR.equals(level)) {
			logger.error("【" + info + "】");
		} else {
			System.out.println("【" + info + "】");
			logger.info("【" + info + "】");
		}
	}
	public static void main(String[] args) {
		LogUtil.log("34");
	}
}
