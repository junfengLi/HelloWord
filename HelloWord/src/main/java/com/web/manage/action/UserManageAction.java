package com.web.manage.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

import com.web.commons.jqgrid.UIPage;
import com.web.commons.util.CommonUtil;
import com.web.commons.util.IsOrEnum;
import com.web.commons.util.UrlBase64Utils;
import com.web.manage.pojo.User;
import com.web.manage.service.UserService;

@RequestMapping("/usermanage")  
@Controller
public class UserManageAction {  
	@Autowired
	private UserService userService;
	
	
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
		String loginName = CommonUtil.getLoginName();
		modelAndView.setViewName(BASE_PATH + module);
		//-------------------业务数据------------------------
		
		
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
    
    
    
    
    
    
    
    
    
    
    
    
    
}  
