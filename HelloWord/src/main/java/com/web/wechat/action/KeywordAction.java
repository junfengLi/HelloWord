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
import com.web.commons.util.BeanCopyUtil;
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
import com.web.wechat.util.KeywordUtil;
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
			record.setServicetype(ServiceTypeEnum.TEXT.getKey());
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
				keyword.setKeyword(KeywordUtil.changeKeyword(keyword.getKeyword(),false));
				String pid = keyword.getId();
				//特殊消息，增加，编辑，查看
				if ("keyword".equals(module)&&StringUtils.isNotBlank(serviceType)) {
					keyword.setKeyword(ServiceTypeEnum.getDescByKey(KeywordUtil.changeKeyword(keyword.getKeyword(),false)));
					if ("Show".equals(type)) {
						model.addAttribute("creatTime", DateUtil.getFormatDateTime(keyword.getCreatetime()));
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
		List<Object> params = new ArrayList<Object>();
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
		keywordText = KeywordUtil.changeKeyword(keywordText, true);
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
		
		StringBuffer hql = new StringBuffer("select k from Keyword k ");
		//消息类型过滤
			hql.append(" , MessageImg i where i.Keywordid = k.id and i.Issite=?");
		hql.append(" and k.wechatId=? and k.serviceType=? ");
			hql.append(" and k.creatTime between ? and ? ");
			hql.append(" and k.creatTime > ? ");
			hql.append(" and k.creatTime < ? ");
			hql.append(" and k.keyword like ? ");
			hql.append(" and k.messageType = ? ");
		System.out.println(hql.toString());
	}
	
	
}
