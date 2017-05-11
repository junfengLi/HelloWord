package com.web.wechat.action;

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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.web.commons.jqgrid.UINode;
import com.web.commons.util.ConfigUtil;
import com.web.upload.pojo.Accessory;
import com.web.upload.service.AccessoryService;
import com.web.wechat.pojo.Wechat;
import com.web.wechat.service.WechatService;
import com.web.wechat.util.WsiteDemoEnum;

@Controller
@RequestMapping("/user/wsite")
public class WSiteAction {
	@Autowired
	private WechatService wechatService;
	@Autowired
	private AccessoryService accessoryService;

	
	private final static String BASE_PATH = "/user/wsite/";
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
			@RequestParam(value="wechatId", defaultValue="")String wechatId,
			@RequestParam(value="demoType", defaultValue="")String demoType,
			Model model,HttpServletRequest request) {	
		model.addAttribute("wechatId", wechatId);
		if ("query".equals(module)) {
			if (StringUtils.isBlank(demoType)) {
				demoType = WsiteDemoEnum.INDEX.getKey();
			}
			List<UINode> UINodes = WsiteDemoEnum.getUINodesBySeq(WsiteDemoEnum.MENU);
			model.addAttribute("demoType", demoType);
			model.addAttribute("UINodes", UINodes);
		} else if ("list".equals(module)) {
			Wechat wechat = wechatService.findByWechatId(wechatId);
			model.addAttribute("wechat", wechat);
			if (StringUtils.isBlank(demoType)) {
				demoType = WsiteDemoEnum.INDEX.getKey();
			}
			List<UINode> UINodes = WsiteDemoEnum.getUINodesBySeq(demoType);
			model.addAttribute("demoType", demoType);
			model.addAttribute("UINodes", UINodes);
		} else if ("banner".equals(module)) {
			List<Accessory> accessorylist = accessoryService.getByLinkIdModule(wechatId, ConfigUtil.MODULE_WECHAT_BANNER);
			model.addAttribute("accessorylist", accessorylist);
//			if (CollectionUtils.isNotEmpty(accessorylist)) {
//				model.addAttribute("accessoryListJson", JsonUtil.convertToJosnWithQuotes(accessorylist));
//			}
		}
		return BASE_PATH+module;
	}
	

	
	@RequestMapping(value = "/setWsiteDemo", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> checkKeyword(@RequestParam(value="demoType", defaultValue = "")String demoType, 
			@RequestParam(value="demo", defaultValue="")String demo,
			@RequestParam(value = "wechatId", defaultValue = "") String wechatId,
			HttpServletRequest request){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		wechatService.setDemo(wechatId, demoType, demo);
		
		resultMap.put("success", true);
		return resultMap;
	}
	
	@RequestMapping(value = "/demoTree", method = RequestMethod.POST)
	@ResponseBody
	public List<UINode> demoTree(@RequestParam(value="demoType", defaultValue="")String demoType,
			HttpServletRequest request){
		List<UINode> UINodes = WsiteDemoEnum.getUINodesBySeq(demoType);
		return UINodes;
	}
	
	@RequestMapping(value = "/saveWechatBanner", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> saveWechatBanner(@RequestParam(value="accessoryIds", defaultValue = "")String accessoryIds, 
			@RequestParam(value = "wechatId", defaultValue = "") String wechatId,
			HttpServletRequest request){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		if (StringUtils.isNotBlank(accessoryIds)) {
			accessoryService.updateLinkId(accessoryIds, wechatId);
		}
		
		resultMap.put("success", true);
		return resultMap;
	}
	
}
