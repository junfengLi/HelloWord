package com.web.wechat.service;

import com.web.wechat.pojo.MessageImage;

public interface MessageImageService {
	
	public MessageImage findMessageImageById(String id);
	public MessageImage saveMessageImage(MessageImage messageImage);
	public MessageImage findMessageImageByPid(String id);
	public void deleteByPid(String pid);
}
