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

import com.web.commons.jqgrid.AdditionalParameters;
import com.web.commons.jqgrid.Item;
import com.web.commons.jqgrid.TreeRespVO;
import com.web.commons.jqgrid.UINode;
import com.web.commons.jqgrid.UIPage;
import com.web.commons.util.ConfigUtil;
import com.web.commons.util.DateUtil;
import com.web.commons.util.IsOrEnum;
import com.web.commons.util.UrlBase64Utils;
import com.web.upload.pojo.Accessory;
import com.web.upload.service.AccessoryService;
import com.web.wechat.pojo.Keyword;
import com.web.wechat.pojo.MessageImg;
import com.web.wechat.pojo.Wechat;
import com.web.wechat.service.KeywordService;
import com.web.wechat.service.MessageImgService;
import com.web.wechat.service.WechatService;
import com.web.wechat.util.WsiteDemoEnum;

@Controller
@RequestMapping("/module")
public class ModuleAction {
	@Autowired
	private WechatService wechatService;
	@Autowired
	private KeywordService keywordService;
	@Autowired
	private MessageImgService messageImgService;
	@Autowired
	private AccessoryService accessoryService;
	
	private final static String BASE_PATH = "/manage/user/module/";
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
			@RequestParam(value = "menuUid", defaultValue = "") String menuUid,
			@RequestParam(value="wechatId", defaultValue="")String wechatId, Model model,HttpServletRequest request) {	
		model.addAttribute("menuUid", menuUid);
		model.addAttribute("wechatId", wechatId);
		return BASE_PATH+module;
	}
	
	@RequestMapping("/{module}/{type}/forward")
	public String addForward(@PathVariable("module")String module,
			@PathVariable("type")String type,
			@RequestParam(value = "menuUid", defaultValue = "") String menuUid,
			@RequestParam(value="id", defaultValue="")String id,
			@RequestParam(value="wechatId", defaultValue="")String wechatId,
			Model model,HttpServletRequest request) {	
		model.addAttribute("menuUid", menuUid);
		model.addAttribute("wechatId", wechatId);
		model.addAttribute("module", module);
		Wechat wechat = wechatService.findByWechatId(wechatId);
		model.addAttribute("wechat", wechat);
		if ("menu".equals(module)) {
			List<UINode> nodes = WsiteDemoEnum.getUINodesBySeq("list");
			model.addAttribute("nodes", nodes);
		}
		
		if (StringUtils.isNotBlank(id)) {
			MessageImg messageImg = messageImgService.findMessageImgById(id);
			if (messageImg != null) {
				model.addAttribute("isMessage", messageImg.getIsmessage());
				if ("Show".equals(type)) {
					model.addAttribute("updateTime", DateUtil.getFormatDateTime(messageImg.getUpdatetime()));
					model.addAttribute("demo", WsiteDemoEnum.getDescByKey(messageImg.getDemo()));
				} else {
					String demo = messageImg.getDemo();
					if (StringUtils.isBlank(demo)) {
						if (IsOrEnum.SHI.getKey().equals(messageImg.getIsmenu())) {
							demo = wechat.getListdemo();
						} else {
							demo = wechat.getContentdemo();
						}
					}
					model.addAttribute("demo", demo);
				}
				Accessory accessory = new Accessory();
				List<Accessory> accessories = accessoryService.getByLinkIdModule(id, ConfigUtil.MODULE_WECHAT_IMG);
				if (CollectionUtils.isNotEmpty(accessories)) {
					accessory = accessories.get(0);
					model.addAttribute("accessory", accessory);
				}
				if (IsOrEnum.SHI.getKey().equals(messageImg.getIsmessage())&&StringUtils.isNotBlank(messageImg.getKeywordid())) {
					Keyword keyword = keywordService.findKeywordById(messageImg.getKeywordid());
					model.addAttribute("keyword", keyword);
				}
				model.addAttribute("messageImg", messageImg);
				
			}
		} else {
			model.addAttribute("isMessage", "0");
		}
		return BASE_PATH+module + type;
	}
	
	@RequestMapping("/fromMessage")
	public String addForward(@RequestParam(value="wechatId", defaultValue="")String wechatId,
			@RequestParam(value = "menuUid", defaultValue = "") String menuUid,
			Model model,HttpServletRequest request) {	
		model.addAttribute("wechatId", wechatId);
		model.addAttribute("menuUid", menuUid);
		return BASE_PATH+"fromMessage";
	}
	
	
	@RequestMapping(value = "listInfo",method = RequestMethod.POST)
	@ResponseBody 
	public UIPage listInfo(
			@RequestParam(value = "page", defaultValue = "1") int pageNumber,
			@RequestParam(value = "rows", defaultValue = "10") int pageSize,
			@RequestParam(value = "wechatId", defaultValue = "") String wechatId,
			@RequestParam(value = "menuUid", defaultValue = "") String menuUid,
			Model model,HttpServletRequest request,HttpServletResponse response){
		MessageImg record = new MessageImg();
		record.setIssite(IsOrEnum.SHI.getKey());
		record.setWechatid(wechatId);
		String createTimeStart = request.getParameter("createTimeStart");
		String createTimeEnd = request.getParameter("createTimeEnd");
		String title = request.getParameter("title");
		String isMenu = request.getParameter("isMenu");
		
		long start = StringUtils.isBlank(createTimeStart)?0:DateUtil.getLongDateFromString(createTimeStart);
		long end = StringUtils.isBlank(createTimeEnd)?System.currentTimeMillis():DateUtil.getLongDateFromString(createTimeEnd,true);	
		//时间过滤
		if (StringUtils.isNotBlank(createTimeStart)) {
			record.setUpdatetime1(start);
		} else if (StringUtils.isNotBlank(createTimeEnd)){
			record.setUpdatetime2(end);
		}
		
		//标题过滤
		if (StringUtils.isNotBlank(title)) {
			record.setTitle("%" +title +"%");
		}
		
		if (StringUtils.isNotBlank(menuUid) && !"all".equals(menuUid)) {
			record.setMenuUidLike(menuUid + "%");
		}
		
		//标题模块类型
		if (StringUtils.isNotBlank(isMenu)) {
			record.setIsmenu(isMenu);
		}
		UIPage UIPage = messageImgService.getPage(record, pageNumber, pageSize);
		return UIPage;
	}
	

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> save(Keyword	keyword, MessageImg messageImg, Model model, HttpServletRequest request){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		String isMessage = messageImg.getIsmessage();
		if (IsOrEnum.SHI.getKey().equals(isMessage)) {
			if (StringUtils.isNotBlank(messageImg.getId())) {
				String keywordId = request.getParameter("keywordId");
				keyword.setId(keywordId);
			}
			keyword = keywordService.saveKeyword(keyword);
			messageImg.setKeywordid(keyword.getId()); 
			
		}
		
		if (StringUtils.isNotBlank(messageImg.getId())) {
			MessageImg oldMessageImg = messageImgService.findMessageImgById(messageImg.getId());
			if (oldMessageImg != null) {
				if (StringUtils.isBlank(messageImg.getMenuuid())) {
					String parentMenuUid = request.getParameter("parentMenuUid");
					String newMenuUid = messageImgService.getNewMenuUid(parentMenuUid, keyword.getWechatid());
					oldMessageImg.setMenuuid(newMenuUid);
				}
				oldMessageImg.setIssite(IsOrEnum.SHI.getKey());
				oldMessageImg.setTitle(messageImg.getTitle());
				oldMessageImg.setDemo(messageImg.getDemo());
				oldMessageImg.setIsmessage(messageImg.getIsmessage());
				oldMessageImg.setDescription(messageImg.getDescription());
				oldMessageImg.setContent(messageImg.getContent());
				oldMessageImg.setSeturl(messageImg.getSeturl());
				oldMessageImg.setImgsort(messageImg.getImgsort());
				messageImg = oldMessageImg;
			}
		} else {
			String newMenuUid = messageImgService.getNewMenuUid(messageImg.getMenuuid(), keyword.getWechatid());
			messageImg.setMenuuid(newMenuUid);
		}
		
		messageImg = messageImgService.saveMessageImg(messageImg);
		String accessoryIds = request.getParameter("accessoryIds");
		if (StringUtils.isNotBlank(accessoryIds)) {
			accessoryService.deleteByLinkId(messageImg.getId());
			accessoryService.updateLinkId(accessoryIds, messageImg.getId());
		}
		resultMap.put("success", true);
		return resultMap;
	}
	@RequestMapping(value = "/menuSave", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> menuSave( MessageImg messageImg, Model model, HttpServletRequest request){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		if (StringUtils.isBlank(messageImg.getId())) {
			String newMenuUid = messageImgService.getNewMenuUid(messageImg.getMenuuid(), messageImg.getWechatid());
			messageImg.setMenuuid(newMenuUid);
		} else {
			MessageImg oldMessageImg = messageImgService.findMessageImgById(messageImg.getId());
			if (oldMessageImg != null) {
				oldMessageImg.setTitle(messageImg.getTitle());
				oldMessageImg.setImgsort(messageImg.getImgsort());
				oldMessageImg.setDemo(messageImg.getDemo());
				oldMessageImg.setDescription(messageImg.getDescription());
				
				messageImg = oldMessageImg;
			}
		}
		messageImg = messageImgService.saveMessageImg(messageImg);
		String accessoryIds = request.getParameter("accessoryIds");
		if (StringUtils.isNotBlank(accessoryIds)) {
			accessoryService.deleteByLinkId(messageImg.getId());
			accessoryService.updateLinkId(accessoryIds, messageImg.getId());
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
				MessageImg messageImg = messageImgService.findMessageImgById(id);
				if (messageImg != null) {
					if (IsOrEnum.SHI.getKey().equals(messageImg.getImage())) {
						keywordService.deleteByIds(messageImg.getKeywordid());
					} else {
						messageImgService.deleteById(id);
					}
				}
			}
		}
		resultMap.put("success", true);
		return resultMap;
	}
	
	@RequestMapping(value = "/selectTree", method = RequestMethod.GET)
	@ResponseBody
	public TreeRespVO selectTree(@RequestParam(value="id", defaultValue = "")String id,
			@RequestParam(value="wechatId", defaultValue = "")String wechatId,HttpServletRequest request){
		TreeRespVO vo = new TreeRespVO();  
		List<Item> voItemList = new ArrayList<Item>();
		if (StringUtils.isBlank(id)) {
			Item item = new Item();  
			item .setType("folder" );//有子节点  
			item .setName("全部");  
			AdditionalParameters adp = new AdditionalParameters();  
            adp .setId("all");  
            adp.setItemSelected(true);
            item .setAdditionalParameters(adp);
            voItemList .add(item);
			vo.setData(voItemList);
		} else if ("all".equals(id)) {
			id = "";
			List<MessageImg> messageImgs = messageImgService.findMessageImgByMenuUid(id, wechatId, true, true);
	        if (CollectionUtils.isNotEmpty(messageImgs)) {
	        	for (MessageImg messageImg : messageImgs) {
	        		Item item = new Item();  
	        		item .setName(messageImg .getTitle());  
	        		List<MessageImg> submessageImgs = messageImgService.findMessageImgByMenuUid(messageImg.getMenuuid(), wechatId, true, true);
	        		if (CollectionUtils.isEmpty(submessageImgs)) {
	        			AdditionalParameters adp = new AdditionalParameters();  
	                    adp .setId(messageImg.getMenuuid());  
	                    item .setAdditionalParameters(adp );  
	                    item .setType("item" );//无子节点  
					} else {
						 item .setType("folder" );//有子节点  
	                     AdditionalParameters adp = new AdditionalParameters();  
	                     adp .setId(messageImg.getMenuuid());  
	                     item .setAdditionalParameters(adp );  
					}
	        		voItemList .add(item);  
	        	}
	        	vo.setData( voItemList ); 
	        }
		} else {
			List<MessageImg> messageImgs = messageImgService.findMessageImgByMenuUid(id, wechatId, true, true);
	        if (CollectionUtils.isNotEmpty(messageImgs)) {
	        	for (MessageImg messageImg : messageImgs) {
	        		Item item = new Item();  
	        		item .setName(messageImg .getTitle());  
	        		List<MessageImg> submessageImgs = messageImgService.findMessageImgByMenuUid(messageImg.getMenuuid(), wechatId, true, true);
	        		if (CollectionUtils.isEmpty(submessageImgs)) {
	        			AdditionalParameters adp = new AdditionalParameters();  
	                    adp .setId(messageImg.getMenuuid());  
	                    item .setAdditionalParameters(adp );  
	                    item .setType("item" );//无子节点  
					} else {
						 item .setType("folder" );//有子节点  
	                     AdditionalParameters adp = new AdditionalParameters();  
	                     adp .setId(messageImg.getMenuuid());  
	                     item .setAdditionalParameters(adp );  
					}
	        		voItemList .add(item);  
	        	}
	        	vo.setData( voItemList ); 
	        }
		}
		/*
		 
		}
		*/
		return vo;
	}
	
}
