package com.web.wechat.service.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.web.wechat.dao.MessageTextDao;
import com.web.wechat.pojo.MessageText;
import com.web.wechat.service.MessageTextService;

@Service("messageTextService")
@Transactional(propagation = Propagation.NOT_SUPPORTED,readOnly=true)
public class MessageTextServiceImpl implements MessageTextService {
	@Autowired
	private MessageTextDao messageTextDao;

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public MessageText saveMessageText(MessageText messageText) {
		messageText.setCreatetime(System.currentTimeMillis());
		if (StringUtils.isBlank(messageText.getId())) {
			messageTextDao.insertSelective(messageText);
		} else {
			messageTextDao.updateByPrimaryKeySelective(messageText);
		}
		return messageText;
	}

	@Override
	public MessageText findMessageTextByPid(String pid) {
		MessageText record = new MessageText();
		record.setPid(pid);
		List<MessageText> messageTexts = messageTextDao.selectByStatement(record);
		if (CollectionUtils.isNotEmpty(messageTexts)) {
			return messageTexts.get(0);
		}
		return null;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void deleteByPid(String pid) {
		messageTextDao.deleteByPid(pid);
	}

	
	


}
