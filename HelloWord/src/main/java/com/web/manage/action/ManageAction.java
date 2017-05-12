package com.web.manage.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.web.commons.util.CommonUtil;
import com.web.manage.pojo.User;
import com.web.manage.service.UserService;
import com.web.wechat.pojo.Wechat;
import com.web.wechat.service.WechatService;

@Controller
public class ManageAction {  
	@Autowired
	private UserService userService;
	@Autowired
	private WechatService wechatService;
	
	private static final String BASE_PATH = "/manage/main/";
    @RequestMapping({"/manage"})  
    public String index(Model model,@RequestParam(value="addWechat", defaultValue="") String addWechat) {  
		String loginName = CommonUtil.getLoginName();
		User user = userService.findByLoginName(loginName);
		model.addAttribute("user", user);
		Wechat wechat = wechatService.findByUserId(loginName);
		model.addAttribute("wechat", wechat);
		model.addAttribute("addWechat", addWechat);
		return "/manage/index";  
    }

    /**
     * 页面加载
     * @param module
     * @param model
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/manage/{module}/load")
    @ResponseBody
    public ModelAndView load(@PathVariable("module")String module, 
    		Model model,HttpServletRequest request,HttpServletResponse response) {  
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName(BASE_PATH + module);
		modelAndView.addObject(model);
		
		return modelAndView;
	}
}  
