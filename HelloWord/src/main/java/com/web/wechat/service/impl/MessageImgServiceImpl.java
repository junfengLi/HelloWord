package com.web.wechat.service.impl;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.web.commons.dao.BaseDao;
import com.web.commons.jqgrid.UIPage;
import com.web.commons.util.BeanCopyUtil;
import com.web.commons.util.ConfigUtil;
import com.web.commons.util.DateUtil;
import com.web.commons.util.IsOrEnum;
import com.web.upload.pojo.Accessory;
import com.web.upload.service.AccessoryService;
import com.web.wechat.dao.MessageImgDao;
import com.web.wechat.pojo.Keyword;
import com.web.wechat.pojo.MessageImg;
import com.web.wechat.service.MessageImgService;

@Component("messageImgService")
@Transactional(propagation = Propagation.NOT_SUPPORTED,readOnly=true)
public class MessageImgServiceImpl implements MessageImgService {
	@Autowired
	private MessageImgDao messageImgDao;
	
	@Autowired
	private AccessoryService accessoryService;

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public MessageImg saveMessageImg(MessageImg messageImg) {
		messageImg.setUpdatetime(System.currentTimeMillis());
		if (StringUtils.isBlank(messageImg.getId())) {
			messageImg.setCreatetime(System.currentTimeMillis());
			messageImgDao.insertSelective(messageImg);
		} else {
			messageImgDao.updateByPrimaryKeySelective(messageImg);
		}
		return messageImg;
	}

	@Override
	public MessageImg findMessageImgByKeywordId(String keywordId){
		MessageImg record = new MessageImg();
		record.setKeywordid(keywordId);
		List<MessageImg> messageImgs = messageImgDao.selectByStatement(record);
		if (CollectionUtils.isNotEmpty(messageImgs)) {
			return messageImgs.get(0);
		}
		return null;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void deleteByKeywordId(String keywordId) {
		MessageImg messageImg = findMessageImgByKeywordId(keywordId);
		if (messageImg != null) {
			accessoryService.deleteByLinkId(messageImg.getId());
			messageImgDao.deleteByPrimaryKey(messageImg.getId());
		}
	}

	@Override
	public List<MessageImg> findMessageImgByMenuUid(String menuUid, String wechatId, boolean fiterMenu, boolean isForTree) {
		MessageImg record = new MessageImg();
		record.setWechatid(wechatId);
		record.setIssite(IsOrEnum.SHI.getKey());
		if (isForTree) {
			record.setMenuUidLike(menuUid + "___");
		} else {
			record.setMenuUidLike(menuUid + "%");
		}
		if (fiterMenu) {
			record.setIsmenu(IsOrEnum.SHI.getKey());
		}
		return  messageImgDao.selectByStatement(record);
	}

	public String getNewMenuUid(String menuUid, String wechatId){
		String newMenuUid = "";
		MessageImg record = new MessageImg();
		record.setWechatid(wechatId);
		record.setMenuUidLike(menuUid + "___");
		String thisMenuUid = messageImgDao.selectMaxUid(record);	
		if (StringUtils.isBlank(thisMenuUid)) {
			newMenuUid = menuUid + "001";
		} else {
			int i = Integer.parseInt(thisMenuUid);
			i++;
			thisMenuUid = thisMenuUid.replaceAll("[0-9]", "0");
			DecimalFormat df = new DecimalFormat(thisMenuUid);
			newMenuUid = df.format(i);
		}
		return newMenuUid;
	}

	@Override
	public UIPage getPage(MessageImg record, int pageNumber, int pageSize) {
		UIPage page = new UIPage(String.valueOf(pageSize));
		List<Map<String,Object>> rows=new ArrayList<Map<String,Object>>();
		long count = messageImgDao.selectByStatementCount(record);
		record.setRows(pageSize);
		record.setOffset(pageNumber);
		List<MessageImg> messageImgs = messageImgDao.selectByStatement(record);
		for (MessageImg messageImg : messageImgs) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", messageImg.getId());
			map.put("title", messageImg.getTitle());
			String isMenu = messageImg.getIsmenu();
			if (StringUtils.isNotBlank(isMenu) && IsOrEnum.SHI.getKey().equals(isMenu)) {
				map.put("module", "模块列表");
			} else {
				map.put("module", "模块内容");
			}
			map.put("imgSort", messageImg.getImgsort());
			map.put("creatTime", DateUtil.getFormatDateTime(messageImg.getCreatetime()));
			map.put("isMessage", messageImg.getIsmessage());
			map.put("isMenu", messageImg.getIsmenu());
			map.put("menuUid", messageImg.getMenuuid());
			map.put("keywordId", messageImg.getKeywordid());
			rows.add(map);
		}
		page.setRows(rows);
    	page.setRecords(count -1);
		return page;
	}
	
	@Override
	public MessageImg findMessageImgById(String id) {
		return messageImgDao.selectByPrimaryKey(id);
	}

	public void deleteById(String id){
		messageImgDao.deleteByPrimaryKey(id);
	}

	@Override
	public UIPage getPageByMenuUid(String menuUid, String wechatId,
			int pageNumber, int pageSize) {
		MessageImg record = new MessageImg();
		record.setWechatid(wechatId);
		record.setIssite(IsOrEnum.SHI.getKey());
		record.setMenuUidLike(menuUid + "___");
		
		UIPage page = new UIPage(String.valueOf(pageSize));
		List<Map<String,Object>> rows=new ArrayList<Map<String,Object>>();
		long count = messageImgDao.selectByStatementCount(record);
		record.setRows(pageSize);
		record.setOffset(pageNumber);
		List<MessageImg> messageImgs = messageImgDao.selectByStatement(record);
		for (MessageImg messageImg : messageImgs) {
			Map<String, Object> map = new HashMap<String, Object>();
			map =  BeanCopyUtil.CopyBeanToMap(messageImg, false);
			List<Accessory> images = accessoryService.getByLinkIdModule(
					messageImg.getId(), ConfigUtil.MODULE_WECHAT_IMG);
			if (CollectionUtils.isNotEmpty(images)) {
				map.put("image", images.get(0).getUrl());
			}
			map.put("creatTime", DateUtil.getFormatDateTime(messageImg.getCreatetime()));
			map.put("updateTime", DateUtil.getFormatDateTime(messageImg.getUpdatetime()));
			rows.add(map);
		}
		page.setRows(rows);
    	page.setRecords(count -1);
		return page;
	}

	@Override
	public MessageImg findByMenuUidAndWechatId(String menuUid, String wechatId) {
		MessageImg record = new MessageImg();
		record.setWechatid(wechatId);
		record.setMenuuid(menuUid);
		List<MessageImg> messageImgs = messageImgDao.selectByStatement(record);
		if (CollectionUtils.isNotEmpty(messageImgs)) {
			return messageImgs.get(0);
		}
		return null;
	}
}
