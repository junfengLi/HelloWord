package com.web.wechat.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.web.commons.dao.BaseDao;
import com.web.wechat.dao.MessageImageDao;
import com.web.wechat.pojo.MessageImage;
import com.web.wechat.service.MessageImageService;

@Component("messageImageService")
@Transactional(propagation = Propagation.NOT_SUPPORTED,readOnly=true)
public class MessageImageServiceImpl implements MessageImageService {
	@Autowired
	private MessageImageDao messageImageDao;
	
	@Override
	public MessageImage findMessageImageById(String id) {
		return messageImageDao.selectByPrimaryKey(id);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public MessageImage saveMessageImage(MessageImage messageImage) {
		messageImage.setCreatetime(System.currentTimeMillis());
		if (StringUtils.isBlank(messageImage.getId())) {
			messageImageDao.insertSelective(messageImage);
		} else {
			messageImageDao.updateByPrimaryKeySelective(messageImage);
		}
		return messageImage;
	}

	@Override
	public MessageImage findMessageImageByPid(String pid) {
		MessageImage record = new MessageImage();
		record.setPid(pid);
		List<MessageImage> messageImages = messageImageDao.selectByStatement(record);
		if (CollectionUtils.isNotEmpty(messageImages)) {
			return messageImages.get(0);
		} else {
			return null;
		}
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void deleteByPid(String pid) {
		messageImageDao.deleteByPid(pid);
	}

	
	


}
