package com.web.wap.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.web.commons.util.BeanCopyUtil;
import com.web.commons.util.ConfigUtil;
import com.web.commons.util.IsOrEnum;
import com.web.commons.util.UrlBase64Utils;
import com.web.upload.pojo.Accessory;
import com.web.upload.service.AccessoryService;
import com.web.wechat.pojo.MessageImg;
import com.web.wechat.pojo.Wechat;
import com.web.wechat.service.MessageImgService;
import com.web.wechat.service.WechatService;

@Controller
@RequestMapping("/wap")
public class WapIndexAction {
	@Autowired
	private WechatService wechatService;
	@Autowired
	private MessageImgService messageImgService;
	@Autowired
	private AccessoryService accessoryService;
	
	private final static String BASE_PATH = "/wap/";
	/**
	 * query跳转
	 * @author LI
	 * 2015-6-24 下午7:04:21
	 * @param flag
	 * @param request
	 * @return
	 */
	@RequestMapping("/index/{wechatId}")
	public String index(@PathVariable("wechatId")String wechatId,
			Model model,HttpServletRequest request) {	
		model.addAttribute("wechatId", wechatId);
		wechatId = UrlBase64Utils.decode(wechatId);
		Wechat wechat = wechatService.findByWechatId(wechatId);
		model.addAttribute("wechat", wechat);
		List<Accessory> accessorylist = accessoryService.getByLinkIdModule(
				wechatId, ConfigUtil.MODULE_WECHAT_BANNER);
		if (CollectionUtils.isNotEmpty(accessorylist)) {
			model.addAttribute("accessorylist", accessorylist);
		}
		setMenuModel(model, wechatId);
		
		return BASE_PATH + "index/" + wechat.getIndexdemo();
	}
	
	@RequestMapping("/list/{wechatId}/{menuUid}")
	public String list(@PathVariable("wechatId")String wechatId,
			@PathVariable("menuUid")String menuUid,
			Model model,HttpServletRequest request) {	
		return "redirect:/wap/list/" + wechatId + "/" + menuUid + "/p_1";
	}
	
	@RequestMapping("/list/{wechatId}/{menuUid}/p_{pageNumber}")
	public String listPage(@PathVariable("wechatId")String wechatId,
			@PathVariable("menuUid")String menuUid,
			@PathVariable("pageNumber")int pageNumber,
			Model model,HttpServletRequest request) {	
		model.addAttribute("wechatId", wechatId);
		model.addAttribute("menuUid", menuUid);
		model.addAttribute("pageNumber", pageNumber);
		wechatId = UrlBase64Utils.decode(wechatId);
		Wechat wechat = wechatService.findByWechatId(wechatId);
		model.addAttribute("wechat", wechat);
		String path = BASE_PATH+"list/" + wechat.getListdemo();
		
		
		setMenuModel(model, wechatId);
		MessageImg messageImg = messageImgService.findByMenuUidAndWechatId(menuUid, wechatId);
		if (messageImg != null && StringUtils.isNotBlank(messageImg.getDemo())&&messageImg.getDemo().startsWith("list_")) {
			path = BASE_PATH+"list/" + messageImg.getDemo();
		}
		
//		EasyUIPage easyUIPage = messageImgService.getPageByMenuUid(menuUid, wechatId, pageNumber, ConfigUtil.PAGESIZE_WAP);
//		model.addAttribute("maps", easyUIPage.getRows());
//		int totalPage = (int)easyUIPage.getTotal()/ConfigUtil.PAGESIZE_WAP + 1;
		int nextPage = pageNumber+1;
		int prevPage = pageNumber-1;
//		model.addAttribute("totalPage", totalPage);
		model.addAttribute("nextPage", nextPage);
		model.addAttribute("prevPage", prevPage);
		
		return path;
	}
	
	
	
	@SuppressWarnings("unchecked")
	private void setMenuModel(Model model, String wechatId){
		List<MessageImg> messageImgs = messageImgService.findMessageImgByMenuUid("", wechatId,false, true);
		List<Map<String, Object>> maps = new ArrayList<Map<String,Object>>();
		for (MessageImg messageImg : messageImgs) {
			Map<String, Object> map = new HashMap<String, Object>();
			map =  BeanCopyUtil.CopyBeanToMap(messageImg, false);
			List<Accessory> images = accessoryService.getByLinkIdModule(
					messageImg.getId(), ConfigUtil.MODULE_WECHAT_IMG);
			if (CollectionUtils.isNotEmpty(images)) {
				map.put("image", images.get(0).getUrl());
			}
			maps.add(map);
		}
		model.addAttribute("menus", maps);
		
	}
	@SuppressWarnings("unchecked")
	@RequestMapping("/show/{wechatId}/{id}")
	public String show(@PathVariable("wechatId")String wechatId,
			@PathVariable("id")String id,
			Model model,HttpServletRequest request) {	
		model.addAttribute("wechatId", wechatId);
		wechatId = UrlBase64Utils.decode(wechatId);
		Wechat wechat = wechatService.findByWechatId(wechatId);
		String path = BASE_PATH+"show/" + wechat.getContentdemo();
		
		if (wechat != null) {
			model.addAttribute("wechat", wechat);
			List<Accessory> accessorylist = accessoryService.getByLinkIdModule(
					wechat.getId(), ConfigUtil.MODULE_WECHAT_HEAD);
			if (CollectionUtils.isNotEmpty(accessorylist)) {
				model.addAttribute("wechatImage", accessorylist.get(0).getUrl());
			}
		}
		setMenuModel(model, wechatId);
		MessageImg messageImg = messageImgService.findMessageImgById(id);
		if (messageImg != null) {
			Map<String, Object> img = new HashMap<String, Object>();
			img = BeanCopyUtil.CopyBeanToMap(messageImg, false);
			model.addAttribute("img", img);
			if (IsOrEnum.SHI.getKey().equals(messageImg.getIssite())) {
				if (StringUtils.isNotBlank(messageImg.getDemo())&&messageImg.getDemo().startsWith("con_")) {
					path = BASE_PATH+"show/" + messageImg.getDemo();
				}
			} else {
				path = BASE_PATH+"show/con_001";
			}
		}
		return path;
	}
	
	public static void main(String[] args) {
		System.out.println(UrlBase64Utils.encode("gh_b20da1b314b6"));
	}
}
