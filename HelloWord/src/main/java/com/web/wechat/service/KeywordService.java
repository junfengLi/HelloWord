package com.web.wechat.service;


import java.util.List;
import java.util.Map;

import com.web.commons.jqgrid.UIPage;
import com.web.wechat.pojo.Keyword;

public interface KeywordService {
	
	public Keyword saveKeyword(Keyword keyword);
	public Keyword findKeywordById(String id);
	public UIPage getPage(Keyword keyword, int pageNum, int pageSize);
	public List<Keyword> findList(Keyword keyword);
	public boolean deleteByIds(String ids);
	public Map<String, Object> findByKeyword(Map<String,String> requestMap, String keyword,String wechatId);
	public List<Keyword> findByKeywordType(String keyword, String wechatId);
//	public List<Keyword> findByKeywordType(String keyword, String keywordId, String wechatId);
}
