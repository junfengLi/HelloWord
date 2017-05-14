package com.web.wechat.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.web.commons.jqgrid.UIPage;
import com.web.commons.util.ConfigUtil;
import com.web.commons.util.DateUtil;
import com.web.commons.util.IsOrEnum;
import com.web.commons.util.JsonTools;
import com.web.open.support.AccessToken;
import com.web.open.util.WechatUtil;
import com.web.upload.pojo.Accessory;
import com.web.upload.service.AccessoryService;
import com.web.wechat.pojo.Keyword;
import com.web.wechat.pojo.MessageImage;
import com.web.wechat.pojo.MessageImg;
import com.web.wechat.pojo.MessageText;
import com.web.wechat.pojo.Wechat;
import com.web.wechat.service.KeywordService;
import com.web.wechat.service.MessageImageService;
import com.web.wechat.service.MessageImgService;
import com.web.wechat.service.MessageTextService;
import com.web.wechat.service.WechatService;
import com.web.wechat.util.MessageTypeEnum;
import com.web.wechat.util.ServiceTypeEnum;

@Controller
@RequestMapping("/keyword")
public class KeywordAction {
	@Autowired
	private WechatService wechatService;
	@Autowired
	private KeywordService keywordService;
	@Autowired
	private MessageTextService messageTextService;
	@Autowired
	private MessageImgService messageImgService;
	@Autowired
	private MessageImageService messageImageService;
	@Autowired
	private AccessoryService accessoryService;
	
	private final static String BASE_PATH = "/manage/user/keyword/";
	/**
	 * query跳转
	 * @author LI
	 * 2015-6-24 下午7:04:21
	 * @param flag
	 * @param request
	 * @return
	 */
	@RequestMapping("/{module}/forward")
	public String index(@PathVariable("module")String module,
			@RequestParam(value="wechatId", defaultValue="")String wechatId, Model model,HttpServletRequest request) {	
		model.addAttribute("wechatId", wechatId);
		Wechat wechat = wechatService.findByWechatId(wechatId);
		model.addAttribute("wechat", wechat);
		if ("special".equals(module)) {
			List<Map<String, Object>> serviceTypes = ServiceTypeEnum.getList();
			model.addAttribute("serviceTypes", serviceTypes);
			Keyword record = new Keyword();
			record.setWechatid(wechatId);
			record.setNotservicetype(ServiceTypeEnum.TEXT.getKey());
			List<Keyword> keywords = keywordService.findList(record);
			Map<String, Object> map = new HashMap<String, Object>();
			for (Keyword keyword : keywords) {
				String id = keyword.getId(), serviceType = keyword.getServicetype(), messageType = keyword.getMessagetype();
				map.put(serviceType + "id", id);
				map.put(serviceType + "serviceType", ServiceTypeEnum.getDescByKey(serviceType));
				map.put(serviceType + "messageType", messageType);
				map.put(serviceType + "creatTime", DateUtil.getFormatDateTime(keyword.getCreatetime()));
			}
			model.addAttribute("map", map);
			
		}
		return BASE_PATH+module;
	}
	
	@RequestMapping("/{module}/{type}/forward")
	public String addForward(@PathVariable("module")String module,
			@PathVariable("type")String type,
			@RequestParam(value="id", defaultValue="")String id,
			@RequestParam(value="wechatId", defaultValue="")String wechatId,
			@RequestParam(value="serviceType", defaultValue="")String serviceType,
			Model model,HttpServletRequest request) {	
		model.addAttribute("wechatId", wechatId);
		model.addAttribute("module", module);
		Wechat wechat = wechatService.findByWechatId(wechatId);
		model.addAttribute("wechat", wechat);
		if ("keyword".equals(module)&&StringUtils.isNotBlank(serviceType)) {
			model.addAttribute("serviceType", serviceType);
			model.addAttribute("serviceTypeText", ServiceTypeEnum.getDescByKey(serviceType));
		}
		
		if (StringUtils.isNotBlank(id)) {
			Keyword keyword = keywordService.findKeywordById(id);
			if (keyword != null) {
				String pid = keyword.getId();
				//特殊消息，增加，编辑，查看
				if ("keyword".equals(module)&&StringUtils.isNotBlank(serviceType)) {
					keyword.setKeyword(ServiceTypeEnum.getDescByKey(keyword.getKeyword()));
					if ("Show".equals(type)) {
						model.addAttribute("createTime", DateUtil.getFormatDateTime(keyword.getCreatetime()));
						List<Keyword> keywords = keywordService.findByKeywordType(keyword.getMessagetype(), wechatId);
						List<Map<String, Object>> maps = new ArrayList<Map<String,Object>>();
						for (Keyword keywordForList : keywords) {
							Map<String,Object> map = new HashMap<String, Object>();
							map.put("id", keywordForList.getId());
							map.put("messageType", MessageTypeEnum.getDescByKey(keywordForList.getMessagetype()));
							if (MessageTypeEnum.TEXT.getKey().equals(keywordForList.getMessagetype())) {
								MessageText messageText = messageTextService.findMessageTextByPid(keywordForList.getId());
								if (messageText != null) {
									map.put("content", messageText.getContent());
								}
							} else if (MessageTypeEnum.IMG.getKey().equals(keywordForList.getMessagetype())){
								MessageImg messageImg = messageImgService.findMessageImgByKeywordId(keywordForList.getId());
								if (messageImg != null) {
									map.put("content", messageImg.getTitle());
								}
							} else if (MessageTypeEnum.IMAGE.getKey().equals(keywordForList.getMessagetype())){
								MessageImage messageImage = messageImageService.findMessageImageByPid(keywordForList.getId());
								if (messageImage != null) {
									List<Accessory> accessories = accessoryService.getByLinkIdModule(messageImage.getId(),
											ConfigUtil.MODULE_WECHAT_IMAGE);
									if (CollectionUtils.isNotEmpty(accessories)) {
										map.put("content", "<img src=\"" + accessories.get(0).getUrl() + "\" style=\"max-width: 200px;max-height: 130px;\" />");
									}
								}
							}
							maps.add(map);
						}
						model.addAttribute("maps", maps);
					}
				} else {
					if ("Show".equals(type)) {
						model.addAttribute("creatTime", DateUtil.getFormatDateTime(keyword.getCreatetime()));
					}
					if ("text".equals(module)) {
						MessageText messageText = messageTextService.findMessageTextByPid(pid);
						model.addAttribute("messageText", messageText);
					} else if ("img".equals(module)) {
						MessageImg messageImg = messageImgService.findMessageImgByKeywordId(pid);
						if (messageImg != null) {
							Accessory accessory = new Accessory();
							List<Accessory> accessories = accessoryService.getByLinkIdModule(messageImg.getId(), ConfigUtil.MODULE_WECHAT_IMG);
							if (CollectionUtils.isNotEmpty(accessories)) {
								accessory = accessories.get(0);
								model.addAttribute("accessory", accessory);
							}
						}
						model.addAttribute("messageImg", messageImg);
					} else if ("image".equals(module)) {
						MessageImage messageImage = messageImageService.findMessageImageByPid(pid);
						if (messageImage != null) {
							Accessory accessory = new Accessory();
							List<Accessory> accessories = accessoryService.getByLinkIdModule(messageImage.getId(),
									ConfigUtil.MODULE_WECHAT_IMAGE);
							if (CollectionUtils.isNotEmpty(accessories)) {
								accessory = accessories.get(0);
								model.addAttribute("accessory", accessory);
							}
						}
						model.addAttribute("messageImage", messageImage);
					}
					
				}
			}
			model.addAttribute("keyword", keyword);
		}
		return BASE_PATH+module + type;
	}
	
	@RequestMapping(value = "listInfo",method = RequestMethod.POST)
	@ResponseBody 
	public UIPage listInfo(
			@RequestParam(value = "page", defaultValue = "1") int pageNumber,
			@RequestParam(value = "rows", defaultValue = "10") int pageSize,
			@RequestParam(value = "wechatId", defaultValue = "") String wechatId,
			Model model,HttpServletRequest request,HttpServletResponse response){
		String createTimeStart = request.getParameter("createTimeStart");
		String createTimeEnd = request.getParameter("createTimeEnd");
		String messageType = request.getParameter("messageType");
		String keyword = request.getParameter("keyword");
		String Issite = request.getParameter("Issite");
		
		Keyword record = new Keyword();
		record.setWechatid(wechatId);
		//消息类型过滤
		if (StringUtils.isNotBlank(Issite)) {
			record.setIssite(Issite);
		} 
		record.setServicetype(ServiceTypeEnum.TEXT.getKey());
		long start = StringUtils.isBlank(createTimeStart)?0:DateUtil.getLongDateFromString(createTimeStart);
		long end = StringUtils.isBlank(createTimeEnd)?System.currentTimeMillis():DateUtil.getLongDateFromString(createTimeEnd,true);	
		//时间过滤
		if (StringUtils.isNotBlank(createTimeStart)) {
			record.setCreatetime1(start);
		} else if (StringUtils.isNotBlank(createTimeEnd)){
			record.setCreatetime2(end);
		}
		
		//关键词过滤
		if (StringUtils.isNotBlank(keyword)) {
			record.setKeyword("%" + keyword + "%");
		}
		
		//消息类型过滤
		if (StringUtils.isNotBlank(messageType)) {
			record.setMessagetype(messageType);
		}
		
				
		UIPage easyUIPage = keywordService.getPage(record, pageNumber, pageSize);
		return easyUIPage;
	}
	

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> save(Keyword	keyword,
			MessageImg messageImg,
			MessageText messageText,
			MessageImage messageImage,
			Model model, HttpServletRequest request){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		String keywordText = keyword.getKeyword();
		keyword.setKeyword(keywordText);
		keyword = keywordService.saveKeyword(keyword);
		if (MessageTypeEnum.TEXT.getKey().equals(keyword.getMessagetype())) {
			messageText.setPid(keyword.getId()); 
			if (StringUtils.isNotBlank(keyword.getId())) {
				String messageTextId = request.getParameter("messageTextId");
				messageText.setId(messageTextId);
			}
			messageTextService.saveMessageText(messageText);
		} else if (MessageTypeEnum.IMG.getKey().equals(keyword.getMessagetype())) {
			//<img src="data:image/png;base64,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...
			//System.out.println(0);
			messageImg.setKeywordid(keyword.getId()); 
			if (StringUtils.isNotBlank(keyword.getId())) {
				String messageImgId = request.getParameter("messageImgId");
				MessageImg oldMessageImg = messageImgService.findMessageImgById(messageImgId);
				if (oldMessageImg != null) {
					oldMessageImg.setTitle(messageImg.getTitle());
					oldMessageImg.setDescription(messageImg.getDescription());
					oldMessageImg.setSeturl(messageImg.getSeturl());
					oldMessageImg.setContent(messageImg.getContent());
					messageImg = oldMessageImg;
				}
			}
			messageImg = messageImgService.saveMessageImg(messageImg);
			String accessoryIds = request.getParameter("accessoryIds");
			if (StringUtils.isNotBlank(accessoryIds)) {
				accessoryService.deleteByLinkId(messageImg.getId());
				accessoryService.updateLinkId(accessoryIds, messageImg.getId());
			}
		}  else if (MessageTypeEnum.IMAGE.getKey().equals(keyword.getMessagetype())) {
			messageImage.setPid(keyword.getId()); 
			String accessoryIds = request.getParameter("accessoryIds");
			if (StringUtils.isNotBlank(accessoryIds)) {
				Accessory accessory = accessoryService.getAccessoryById(accessoryIds);
				if (accessory != null) {
					AccessToken token = WechatUtil.getToken(keyword.getWechatid());
					String json = WechatUtil.updateWechatImage(accessory.getUrl(), accessory.getFilesize(),
							accessory.getName(), token.getToken(), "image");
					Map<String, Object> map = JsonTools.parseJSON2Map(json);
					String mediaId = (String) map.get("media_id");
					messageImage.setMediaid(mediaId);
					accessory.setText1(mediaId);
					accessoryService.add(accessory);
					if (StringUtils.isNotBlank(keyword.getId())) {
						String messageImageId = request.getParameter("messageImageId");
						MessageImage oldMessageImage = messageImageService.findMessageImageById(messageImageId);
						if (oldMessageImage != null) {
							oldMessageImage.setMediaid(messageImage.getMediaid());
							messageImage = oldMessageImage;
						}
					}
					messageImage = messageImageService.saveMessageImage(messageImage);
				}
			}
			if (StringUtils.isNotBlank(accessoryIds)) {
				accessoryService.deleteByLinkId(messageImage.getId());
				accessoryService.updateLinkId(accessoryIds, messageImage.getId());
			}
		} else {
			
		}
		
		resultMap.put("success", true);
		return resultMap;
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> delete(@RequestParam(value="ids", defaultValue = "")String ids, HttpServletRequest request){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		if (StringUtils.isNotBlank(ids)) {
			String[] idList = ids.split(";");
			for (String id : idList) {
				keywordService.deleteByIds(id);
			}
		}
		resultMap.put("success", true);
		return resultMap;
	}
	
//	@RequestMapping(value = "/checkKeyword", method = RequestMethod.POST)
//	@ResponseBody
//	public Map<String, Object> checkKeyword(@RequestParam(value="keyword", defaultValue = "")String keyword, 
//			@RequestParam(value="Keywordid", defaultValue="")String Keywordid,
//			@RequestParam(value = "wechatId", defaultValue = "") String wechatId,
//			@RequestParam(value="module", defaultValue = "")String module, HttpServletRequest request){
//		Map<String, Object> resultMap = new HashMap<String, Object>();
//		boolean isCheck = true;
//		String checkKeyword = "";
//		if ("text".equals(module)) {
//			keyword = KeywordUtil.changeKeyword(keyword, true);
//			String[] keywords = keyword.split("&");
//			for (int i = 1; i<keywords.length; i++) {
//				
//				List<Keyword> findByKeywordType = keywordService.findByKeywordType(keywords[i], Keywordid, wechatId);
//				if (CollectionUtils.isNotEmpty(findByKeywordType)) {
//					checkKeyword += keywords[i] + "、";
//					isCheck = false;
//				}
//			} 
//			if (StringUtils.isNotBlank(checkKeyword)) {
//				checkKeyword = checkKeyword.substring(0,checkKeyword.length()-1);
//			}
//			checkKeyword ="关键词：“" + checkKeyword + "”已存在回复内容";
//		} else if ("img".equals(module)) {
//			keyword = KeywordUtil.changeKeyword(keyword, true);
//			String checkKeywordText = "", checkKeywordImage = "", checkKeywordImg = "";
//			String[] keywords = keyword.split("&");
//			for (int i = 1; i<keywords.length; i++) {
//				List<Keyword> findByKeywordType = keywordService.findByKeywordType(keywords[i], Keywordid, wechatId);
//				if (CollectionUtils.isNotEmpty(findByKeywordType)) {
//					Keyword setKeyword = findByKeywordType.get(0);
//					if (MessageTypeEnum.TEXT.getKey().equals(setKeyword.getMessagetype())) {
//						checkKeywordText += keywords[i] + "、";
//						isCheck = false;
//					} else if (MessageTypeEnum.IMAGE.getKey().equals(setKeyword.getMessagetype())) {
//						checkKeywordImage += keywords[i] + "、";
//						isCheck = false;
//					} else {
//						if (findByKeywordType.size() > 7) {
//							checkKeywordImg += keywords[i] + "、";
//							isCheck = false;
//						}
//					}
//				}
//			} 
//			if (StringUtils.isNotBlank(checkKeywordText)) {
//				checkKeywordText = checkKeywordText.substring(0,checkKeywordText.length()-1);
//				checkKeyword = "关键词：“" + checkKeywordText + "”已存在文本回复内容。<br>";
//			}
//			if (StringUtils.isNotBlank(checkKeywordImage)) {
//				checkKeywordImage = checkKeywordImage.substring(0,checkKeywordImage.length()-1);
//				checkKeyword = "关键词：“" + checkKeywordImage + "”已存在图片回复内容。<br>";
//			}
//			if (StringUtils.isNotBlank(checkKeywordImg)) {
//				checkKeywordImg = checkKeywordImg.substring(0,checkKeywordImg.length()-1);
//				checkKeyword += "关键词：“" + checkKeywordImg + "”的图文回复条数已到达最大数（8）。";
//			}
//			
//		}  else if ("image".equals(module)) {
//			keyword = KeywordUtil.changeKeyword(keyword, true);
//			String checkKeywordText = "", checkKeywordImage = "", checkKeywordImg = "";
//			String[] keywords = keyword.split("&");
//			for (int i = 1; i<keywords.length; i++) {
//				List<Keyword> findByKeywordType = keywordService.findByKeywordType(keywords[i], Keywordid, wechatId);
//				if (CollectionUtils.isNotEmpty(findByKeywordType)) {
//					Keyword setKeyword = findByKeywordType.get(0);
//					if (MessageTypeEnum.TEXT.getKey().equals(setKeyword.getMessagetype())) {
//						checkKeywordText += keywords[i] + "、";
//						isCheck = false;
//					} else if (MessageTypeEnum.IMG.getKey().equals(setKeyword.getMessagetype())) {
//						checkKeywordImg += keywords[i] + "、";
//						isCheck = false;
//					} else {
//						if (findByKeywordType.size() > 7) {
//							checkKeywordImage += keywords[i] + "、";
//							isCheck = false;
//						}
//					}
//				}
//			} 
//			if (StringUtils.isNotBlank(checkKeywordText)) {
//				checkKeywordText = checkKeywordText.substring(0,checkKeywordText.length()-1);
//				checkKeyword = "关键词：“" + checkKeywordText + "”已存在文本回复内容。<br>";
//			}
//			if (StringUtils.isNotBlank(checkKeywordImg)) {
//				checkKeywordImg = checkKeywordImg.substring(0,checkKeywordImg.length()-1);
//				checkKeyword += "关键词：“" + checkKeywordImg + "”已存在图文回复内容。<br>";
//			}
//			if (StringUtils.isNotBlank(checkKeywordImage)) {
//				checkKeywordImage = checkKeywordImage.substring(0,checkKeywordImage.length()-1);
//				checkKeyword = "关键词：“" + checkKeywordImage + "”的图片回复条数已到达最大数（8）。";
//			}
//		} else if ("keyword".equals(module)) {
//			List<Keyword> findByKeywordType = keywordService.findByKeywordType(keyword, "", wechatId);
//			if (CollectionUtils.isEmpty(findByKeywordType)) {
//				isCheck = false;
//				checkKeyword = "该关键词没有设置回复消息，请换个关键词";
//			}
//		} else {
//			
//		}
//		
//		resultMap.put("success", isCheck);
//		resultMap.put("checkKeyword", checkKeyword);
//		return resultMap;
//	}
	
	
	@RequestMapping(value = "/cancleMenu", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> cancleMenu(@RequestParam(value="id", defaultValue = "")String id, HttpServletRequest request){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		if (StringUtils.isNotBlank(id)) {
			MessageImg messageImg = messageImgService.findMessageImgById(id);
			if (messageImg != null) {
				messageImg.setIssite(IsOrEnum.FOU.getKey());
				messageImg.setMenuuid("");
				messageImgService.saveMessageImg(messageImg);
			}
		}
		resultMap.put("success", true);
		return resultMap;
	}
	
	public static void main(String[] args) {
//		String spring = "&asdfasdf;;;&;;;;asdf&";
//		String[]  id = spring.split("&");
//		for (String string : id) {
//			System.out.println("*********" + string);
//		}
//		
//		StringBuffer hql = new StringBuffer("select k from Keyword k ");
//		//消息类型过滤
//			hql.append(" , MessageImg i where i.Keywordid = k.id and i.Issite=?");
//		hql.append(" and k.wechatId=? and k.serviceType=? ");
//			hql.append(" and k.creatTime between ? and ? ");
//			hql.append(" and k.creatTime > ? ");
//			hql.append(" and k.creatTime < ? ");
//			hql.append(" and k.keyword like ? ");
//			hql.append(" and k.messageType = ? ");
//		System.out.println(hql.toString());
//		
		
	}
	
	
}
