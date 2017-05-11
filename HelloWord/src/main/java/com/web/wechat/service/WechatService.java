package com.web.wechat.service;

import com.web.wechat.pojo.Wechat;

public interface WechatService {

	
	void add(Wechat wechat);
	
	void deleteById(String id);
	void deleteByUserId(String userId);
	
	Wechat findById(String id);
	Wechat findByUserId(String userId);
	Wechat findByWechatId(String wechatId);
	void setDemo(String wechatId, String demoType, String demo);
}
