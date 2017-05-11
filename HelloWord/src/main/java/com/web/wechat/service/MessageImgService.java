package com.web.wechat.service;

import java.util.List;

import com.web.commons.jqgrid.UIPage;
import com.web.wechat.pojo.MessageImg;

public interface MessageImgService {
	
	public MessageImg saveMessageImg(MessageImg messageImg);
	public MessageImg findMessageImgByKeywordId(String keywordId);
	public void deleteByKeywordId(String keywordId);
	public List<MessageImg> findMessageImgByMenuUid(String menuUid, String wechatId, boolean fiterMenu, boolean isForTree);
	
	
	public String getNewMenuUid(String menuUid, String wechatId);
	public UIPage getPage(MessageImg messageImg, int pageNumber, int pageSize);
	public MessageImg findMessageImgById(String id);
	public void deleteById(String id);
	
	public UIPage getPageByMenuUid(String menuUid, String wechatId, int pageNumber, int pageSize);
	public MessageImg findByMenuUidAndWechatId(String menuUid, String wechatId);
	
}
