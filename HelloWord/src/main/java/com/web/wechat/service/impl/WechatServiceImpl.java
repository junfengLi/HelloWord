package com.web.wechat.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.web.commons.dao.BaseDao;
import com.web.wechat.dao.WechatDao;
import com.web.wechat.pojo.Wechat;
import com.web.wechat.service.WechatService;
import com.web.wechat.util.WsiteDemoEnum;

//@Component
@Service("wechatService")
@Transactional
public class WechatServiceImpl implements WechatService {
	@Autowired
	private WechatDao wechatDao;
	
	@Override
	public void add(Wechat wechat) {
		wechat.setUpdatetime(System.currentTimeMillis());
		if (StringUtils.isBlank(wechat.getId())) {
			wechat.setCreatetime(System.currentTimeMillis());
			wechatDao.insertSelective(wechat);
		} else {
			wechatDao.updateByPrimaryKeySelective(wechat);
		}
	}

	@Override
	public void deleteById(String id) {
		wechatDao.deleteByPrimaryKey(id);
	}
	
	@Override
	public void deleteByUserId(String userId) {
		wechatDao.deleteByUserId(userId);
	}

	@Override
	public Wechat findById(String id) {
		return wechatDao.selectByPrimaryKey(id);
	}

	@Override
	public Wechat findByUserId(String userId) {
		Wechat record = new Wechat();
		record.setUserid(userId);
		List<Wechat> wechats = wechatDao.selectByStatement(record);
		if (CollectionUtils.isNotEmpty(wechats)) {
			return wechats.get(0);
		}
		return null;
	}

	@Override
	public Wechat findByWechatId(String wechatId) {
		Wechat record = new Wechat();
		record.setWechatid(wechatId);
		List<Wechat> wechats = wechatDao.selectByStatement(record);
		if (CollectionUtils.isNotEmpty(wechats)) {
			return wechats.get(0);
		}
		return null;
	}

	@Override
	public void setDemo(String wechatId, String demoType, String demo){
		Wechat wechat = findByWechatId(wechatId);
		if (WsiteDemoEnum.INDEX.getKey().equals(demoType)) {
			wechat.setIndexdemo(demo);
		} else if (WsiteDemoEnum.LIST.getKey().equals(demoType)) {
			wechat.setListdemo(demo);
		} else if (WsiteDemoEnum.CONTENT.getKey().equals(demoType)) {
			wechat.setContentdemo(demo);
		}
		wechatDao.updateByPrimaryKeySelective(wechat);
	}
	

}
