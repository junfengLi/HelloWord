package com.web.manage.action;

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
import org.springframework.web.servlet.ModelAndView;

import com.web.commons.util.CommonUtil;
import com.web.commons.util.ConfigUtil;
import com.web.commons.util.UrlBase64Utils;
import com.web.manage.pojo.User;
import com.web.manage.service.UserService;
import com.web.open.support.AccessToken;
import com.web.open.util.WechatUtil;
import com.web.upload.pojo.Accessory;
import com.web.upload.service.AccessoryService;
import com.web.wechat.pojo.Wechat;
import com.web.wechat.service.WechatService;
import com.web.wechat.util.WechatTypeEnum;
import com.web.wechat.util.WsiteDemoEnum;

@RequestMapping("/usermanage")  
@Controller
public class UserManageAction {  
	@Autowired
	private UserService userService;
	@Autowired
	private WechatService wechatService;
	@Autowired
	private AccessoryService accessoryService;
	
	private static final String BASE_PATH = "/manage/user/wechat/";

    /**
     * 页面加载
     * @param module
     * @param model
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/{module}/load")
    @ResponseBody
    public ModelAndView load(@PathVariable("module")String module, 
    		Model model,HttpServletRequest request,HttpServletResponse response) {  
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName(BASE_PATH + module);
//		Accessory a = accessoryService.getAccessoryById("4028928154f67eb50154f68566f80001");
//		model.addAttribute("accessory", a);
		//-------------------业务数据------------------------
		if ("show".equals(module) || "edit".equals(module)) {
			if ("edit".equals(module)) {
				String userId = CommonUtil.getLoginName();
				model.addAttribute("userId", userId);
			}
			String wechatId = request.getParameter("wechatId");
			if (StringUtils.isNotBlank(wechatId)) {
				Wechat wechat = wechatService.findByWechatId(wechatId);
				model.addAttribute("wechat", wechat);
				model.addAttribute("wechatTypeDesc", WechatTypeEnum.getDescByKey(wechat.getWechattype()));
				Accessory accessory = accessoryService.getOneByLinkIdModule(wechat.getId(), ConfigUtil.MODULE_WECHAT_HEAD);
				model.addAttribute("accessory", accessory);
			}      
		}
		//-------------------业务数据-------------------------
		modelAndView.addObject(model);
		return modelAndView;
	}
    
    /**
     * 窗口页面跳转
     * @param module
     * @param model
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/{module}/forward")
    public String forward(@PathVariable("module")String module,
    		@RequestParam(value="id",defaultValue="")String id,
    		Model model,HttpServletRequest request,HttpServletResponse response) {
    	if (StringUtils.isNotBlank(id)) {
			User user = userService.findById(id);
			user.setPassword(UrlBase64Utils.decode(user.getPassword()));
			model.addAttribute("user", user);
		}
    	
        return BASE_PATH + module;  
    }
    
    
    
    
    
    
    
    
    
    
    /**
	 * 微信信息保存
	 * @param wechat
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/wechatSave", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> saveNews(Wechat wechat, Model model,
			@RequestParam(value = "accessoryIds", defaultValue = "") String accessoryIds,
			HttpServletRequest request){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		if (StringUtils.isNotBlank(wechat.getId())) {
			Wechat old = wechatService.findById(wechat.getId());
			old.setWechatfans(wechat.getWechatfans());
			old.setWechatname(wechat.getWechatname());
			old.setWechatnumber(wechat.getWechatnumber());
			old.setWechattype(wechat.getWechattype());
			old.setAppid(wechat.getAppid());
			old.setAppsecred(wechat.getAppsecred());
			wechat = old;
		} else {
			wechat.setIndexdemo(WsiteDemoEnum.INDEX_001.getKey());
			wechat.setListdemo(WsiteDemoEnum.LIST_001.getKey());
			wechat.setContentdemo(WsiteDemoEnum.CON_001.getKey());
		}
		wechatService.add(wechat);
		
		if (StringUtils.isNotBlank(accessoryIds)) {
			accessoryService.deleteByLinkId(wechat.getId());
			accessoryService.updateLinkId(accessoryIds, wechat.getId());
		}
		resultMap.put("success", true);
		resultMap.put("desc", "保存成功！");
		resultMap.put("wechatId", wechat.getWechatid());
		return resultMap;
	}
	
	@RequestMapping(value = "/wechatCheckAppId", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> wechatCheckAppId(@RequestParam(value="appId", defaultValue="")String appId,
			@RequestParam(value="appSecred", defaultValue="")String appSecred,
			HttpServletRequest request){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		String wechatType = WechatTypeEnum.D_W.getKey();
		AccessToken token = WechatUtil.getToken(appId, appSecred);
		if (token == null) {
			resultMap.put("success", false);
		} else {
			wechatType = WechatUtil.getWechatType(token.getToken());
			resultMap.put("success", true);
			resultMap.put("wechatType", wechatType);
			resultMap.put("desc", WechatTypeEnum.getDescByKey(wechatType));
		}
		return resultMap;
	}
	
	
	/***************************私有方法********************************/
	private void setIndexModel(Model model, HttpServletRequest request){
		String userId = CommonUtil.getLoginName();
		Wechat wechat = wechatService.findByUserId(userId);
		model.addAttribute("wechat", wechat);
		if (wechat == null || StringUtils.isBlank(wechat.getId())) {
			model.addAttribute("hasWechat", false);
		} else {
			model.addAttribute("hasWechat", true);
			model.addAttribute("wechatId", wechat.getWechatid());
		}
		
	}
    
    
}  
