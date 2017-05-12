package com.web.wechat.service.impl;

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
import com.web.manage.pojo.User;
import com.web.upload.pojo.Accessory;
import com.web.upload.service.AccessoryService;
import com.web.wechat.dao.KeywordDao;
import com.web.wechat.pojo.Keyword;
import com.web.wechat.pojo.MessageImage;
import com.web.wechat.pojo.MessageImg;
import com.web.wechat.pojo.MessageText;
import com.web.wechat.pojo.MessageImg;
import com.web.wechat.pojo.MessageText;
import com.web.wechat.service.KeywordService;
import com.web.wechat.service.MessageImageService;
import com.web.wechat.service.MessageImgService;
import com.web.wechat.service.MessageTextService;
import com.web.wechat.util.KeywordUtil;
import com.web.wechat.util.MessageTypeEnum;
import com.web.wechat.util.ServiceTypeEnum;


@Service("keywordService")
@Transactional(propagation = Propagation.NOT_SUPPORTED,readOnly=true)
public class KeywordServiceImpl implements KeywordService {
	@Autowired
	private KeywordDao keywordDao;
	@Autowired
	private MessageTextService messageTextService;
	@Autowired
	private MessageImgService messageImgService;
	@Autowired
	private MessageImageService messageImageService;
	@Autowired
	private AccessoryService accessoryService;
	
	/**
	 * 保存关键词
	 */
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public Keyword saveKeyword(Keyword keyword) {
		keyword.setCreatetime(System.currentTimeMillis());
		if (StringUtils.isBlank(keyword.getId())) {
			keywordDao.insertSelective(keyword);
		} else {
			keywordDao.updateByPrimaryKeySelective(keyword);
		}
		return keyword;
	}

	/**
	 * 根据主键获取对象
	 */
	@Override
	public Keyword findKeywordById(String id) {
		return keywordDao.selectByPrimaryKey(id);
	}
	
	/**
	 * 根据hql查询列表数据（包括总条数，分页使用）
	 */
	@SuppressWarnings("unchecked")
	@Override
	public UIPage getPage(Keyword keyword, int pageNum, int pageSize) {
		UIPage page = new UIPage(String.valueOf(pageSize));
		List<Map<String,Object>> rows=new ArrayList<Map<String,Object>>();
		long count = keywordDao.selectByStatementCount(keyword);
		keyword.setRows(pageSize);
		keyword.setOffset(pageNum);
		List<Keyword> keywords = keywordDao.selectByStatement(keyword);
		for (Keyword keyword2 : keywords) {
			Map<String, Object> map = new HashMap<String, Object>();
			String message = "", id = keyword2.getId(), Messagetype = keyword2.getMessagetype();
			map.put("id", id);
			map.put("keyword", KeywordUtil.changeKeyword(keyword2.getKeyword(),false));
			map.put("Messagetype", MessageTypeEnum.getDescByKey(Messagetype));
			map.put("module", Messagetype);
			if (MessageTypeEnum.TEXT.getKey().equals(Messagetype)) {
				MessageText messageText = messageTextService.findMessageTextByPid(id);
				if (messageText != null) {
					message = "[文本消息]：" + messageText.getContent();
				}
			} else if (MessageTypeEnum.IMG.getKey().equals(Messagetype)){
				MessageImg messageImg = messageImgService.findMessageImgByKeywordId(id);
				if (messageImg != null) {
					message =  "[图文消息]：" + messageImg.getTitle();
					map.put("messageId", messageImg.getId());
				}
			} else if (MessageTypeEnum.IMAGE.getKey().equals(Messagetype)){
				MessageImage messageImage = messageImageService.findMessageImageByPid(id);
				if (messageImage != null) {
					message =  "[图片消息]：" + messageImage.getMediaid();
					map.put("messageId", messageImage.getId());
				}
			}
			map.put("message", message);
			map.put("creatTime", DateUtil.getFormatDateTime(keyword2.getCreatetime()));
			
			rows.add(map);
		}
		page.setRows(rows);
    	page.setRecords(count -1);
    	
		return page;
	}
	
	/**
	 * 根据关键词id删除，级联删除管理信息
	 */
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public boolean deleteByIds(String ids) {
		boolean flag = false;
		if (StringUtils.isNotBlank(ids)) {
			String[] idArray = ids.split(";");
			for (String string : idArray) {
				Keyword keyword = findKeywordById(string);
				if (keyword != null) {
					if (MessageTypeEnum.TEXT.getKey().equals(keyword.getMessagetype())) {
						messageTextService.deleteByPid(string);
					}
					if (MessageTypeEnum.IMG.getKey().equals(keyword.getMessagetype())) {
						messageImgService.deleteByKeywordId(string);
					}
					if (MessageTypeEnum.IMAGE.getKey().equals(keyword.getMessagetype())) {
						messageImageService.deleteByPid(string);
					}
					keywordDao.deleteByPrimaryKey(string);
				}
			}
			flag = true;
		}
		return flag;
	}

	/**
	 * 接口调用
	 */
	@Override
	public Map<String, Object> findByKeyword(Map<String,String> request, String keyword, String wechatId) {
		Map<String, Object> requestMap = new HashMap<String, Object>();
		
		List<Keyword> keywords = findByKeywordType(keyword, wechatId);
		if (CollectionUtils.isNotEmpty(keywords)) {
			Keyword keywordObject = keywords.get(0);
			//文本消息
			if (MessageTypeEnum.TEXT.getKey().equals(keywordObject.getMessagetype())){
				MessageText messageText = messageTextService.findMessageTextByPid(keywordObject.getId());
				requestMap.put("Messagetype", MessageTypeEnum.TEXT.getKey());
				requestMap.put("object", messageText);
			} else if (MessageTypeEnum.IMG.getKey().equals(keywordObject.getMessagetype())){//图文消息
				List<MessageImg> messageImgs = new ArrayList<MessageImg>();
				for (Keyword thisKeyword : keywords) {
					MessageImg messageImg = messageImgService.findMessageImgByKeywordId(thisKeyword.getId());
					if (messageImg != null) {
						List<Accessory> accessories = accessoryService.getByLinkIdModule(messageImg.getId(), ConfigUtil.MODULE_WECHAT_IMG);
						if (CollectionUtils.isNotEmpty(accessories)) {
							messageImg.setImage(accessories.get(0).getUrl());
						}
						messageImgs.add(messageImg);
					}
				}
				requestMap.put("Messagetype", MessageTypeEnum.IMG.getKey());
				requestMap.put("object", messageImgs);
			} else if (MessageTypeEnum.IMAGE.getKey().equals(keywordObject.getMessagetype())){//图片消息
				List<MessageImage> messageImages = new ArrayList<MessageImage>();
				for (Keyword thisKeyword : keywords) {
					MessageImage messageImage = messageImageService.findMessageImageByPid(thisKeyword.getId());
					if (messageImage != null) {
						List<Accessory> accessories = accessoryService.getByLinkIdModule(messageImage.getId(), ConfigUtil.MODULE_WECHAT_IMG);
						if (CollectionUtils.isNotEmpty(accessories)) {
							messageImage.setImage(accessories.get(0).getUrl());
						}
						messageImages.add(messageImage);
					}
				}
				requestMap.put("Messagetype", MessageTypeEnum.IMAGE.getKey());
				requestMap.put("object", messageImages);
			}
		} else {
			List<Keyword> defaultKeywords = findByKeywordType(ServiceTypeEnum.DEFAULT.getKey(), wechatId);
			if (CollectionUtils.isNotEmpty(defaultKeywords)) {
				Keyword oldKeyword = defaultKeywords.get(0);
				List<Keyword> keywordObjects = findByKeywordType(oldKeyword.getMessagetype(), wechatId);
				Keyword keywordObject = new Keyword();
				if (CollectionUtils.isNotEmpty(keywordObjects)) {
					keywordObject = keywordObjects.get(0);
				}
				//文本消息
				if (MessageTypeEnum.TEXT.getKey().equals(keywordObject.getMessagetype())){
					MessageText messageText = messageTextService.findMessageTextByPid(keywordObject.getId());
					requestMap.put("Messagetype", MessageTypeEnum.TEXT.getKey());
					requestMap.put("object", messageText);
				} else if (MessageTypeEnum.IMG.getKey().equals(keywordObject.getMessagetype())){//图文消息
					List<MessageImg> messageImgs = new ArrayList<MessageImg>();
					for (Keyword thisKeyword : keywordObjects) {
						MessageImg messageImg = messageImgService.findMessageImgByKeywordId(thisKeyword.getId());
						if (messageImg != null) {
							List<Accessory> accessories = accessoryService.getByLinkIdModule(messageImg.getId(), ConfigUtil.MODULE_WECHAT_IMG);
							if (CollectionUtils.isNotEmpty(accessories)) {
								messageImg.setImage(accessories.get(0).getUrl());
							}
							messageImgs.add(messageImg);
						}
					}
					requestMap.put("Messagetype", MessageTypeEnum.IMG.getKey());
					requestMap.put("object", messageImgs);
				} else if (MessageTypeEnum.IMAGE.getKey().equals(keywordObject.getMessagetype())){//图片消息
					List<MessageImage> messageImages = new ArrayList<MessageImage>();
					for (Keyword thisKeyword : keywordObjects) {
						MessageImage messageImage = messageImageService.findMessageImageByPid(thisKeyword.getId());
						if (messageImage != null) {
							List<Accessory> accessories = accessoryService.getByLinkIdModule(messageImage.getId(), ConfigUtil.MODULE_WECHAT_IMG);
							if (CollectionUtils.isNotEmpty(accessories)) {
								messageImage.setImage(accessories.get(0).getUrl());
							}
							messageImages.add(messageImage);
						}
					}
					requestMap.put("Messagetype", MessageTypeEnum.IMAGE.getKey());
					requestMap.put("object", messageImages);
				}
			} else {
				MessageText messageText = new MessageText();
				messageText.setContent("您发的消息已收到！我们小编会尽快给您回复");
				requestMap.put("Messagetype", MessageTypeEnum.TEXT.getKey());
				requestMap.put("object", messageText);
			}
		}
		return requestMap;
	}


	/**
	 * 根据关键词查询列表
	 */
	@Override
	public List<Keyword> findByKeywordType(String keyword, String wechatId) {
		Keyword k = new Keyword();
		k.setKeyword(keyword);
		k.setWechatid(wechatId);
		return keywordDao.selectByStatement(k);
	}
	
	
//	/**
//	 * 验证关键词是否重复，编辑验证，过滤不是自己id的关键词
//	 */
//	@SuppressWarnings("unchecked")
//	@Override
//	public List<Keyword> findByKeywordType(String keyword, String keywordId, String wechatId) {
//		StringBuffer hql = new StringBuffer(" From Keyword k where k.wechatId=? and k.keyword like ?");
//		List<Object> params = new ArrayList<Object>();
//		params.add(wechatId);
//		params.add("%&" + keyword + "&%");
//		if (StringUtils.isNotBlank(keywordId)) {
//			hql.append(" and k.id!=?");
//			params.add(keywordId);
//		}
//		return (List<Keyword>) super.pageQuery(hql.toString(), params.toArray());
//	}


	@Override
	public List<Keyword> findList(Keyword keyword) {
		return keywordDao.selectByStatement(keyword);
	}
	
	
}
