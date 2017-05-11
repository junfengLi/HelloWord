package com.web.wechat.service;

import com.web.wechat.pojo.MessageText;

public interface MessageTextService {
	
	public MessageText saveMessageText(MessageText messageText);
	public MessageText findMessageTextByPid(String id);
	public void deleteByPid(String pid);
}
