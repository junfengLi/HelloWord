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
import com.web.commons.util.IsOrEnum;
import com.web.commons.util.UrlBase64Utils;
import com.web.manage.pojo.User;
import com.web.manage.service.UserService;

@RequestMapping("/user")  
@Controller
public class UserAction {  
	@Autowired
	private UserService userService;
	
	
	private static final String BASE_PATH = "/manage/sys/user/";

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
    
    
    
    
    
    
    
    
    
    
    
    //--------------------------------------异步数据---------------------------------------------------
    @RequestMapping("/getPage")
    @ResponseBody
    public UIPage getPage(User user,@RequestParam(value="page",defaultValue="1")int pageNum,
			@RequestParam(value="rows",defaultValue="10")int pageSize,
			HttpServletRequest request){
    	UIPage page = userService.getPage(user, pageNum, pageSize); 
    	return page;
    }
    
    @RequestMapping(value="/save", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> save(User user,
			HttpServletRequest request){
    	Map<String, Object> resultMap = new HashMap<>();
    	user.setPassword(UrlBase64Utils.encode(user.getPassword()));
    	userService.saveUser(user);
    	resultMap.put("result", true);
    	resultMap.put("success", true);
    	return resultMap;
    }
    
    
    @RequestMapping(value="/isOnly", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> isOnly(@RequestParam(value="loginName", defaultValue = "") String loginName){
    	Map<String, Object> resultMap = new HashMap<>();
    	if (StringUtils.isNotBlank(loginName)) {
    		User user = userService.findByLoginName(loginName);
    		resultMap.put("result", user == null);
		} else {
			resultMap.put("result", true);
		}
    	return resultMap;
    }
    
    
    @RequestMapping(value="/resetPassword", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> resetPassword(@RequestParam(value="id", defaultValue = "") String id){
    	Map<String, Object> resultMap = new HashMap<>();
    	if (StringUtils.isNotBlank(id)) {
    		User user = userService.findById(id);
    		if (user != null) {
				user.setPassword(UrlBase64Utils.encode("123456"));
				userService.saveUser(user);
				resultMap.put("result", true);
			} else {
				resultMap.put("result", false);
			}
		} else {
			resultMap.put("result", false);
		}
    	return resultMap;
    }
    
    @RequestMapping(value="/lock", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> lock(@RequestParam(value="id", defaultValue = "") String id,
    		@RequestParam(value="open", defaultValue = "") String open){
    	Map<String, Object> resultMap = new HashMap<>();
    	if (StringUtils.isNotBlank(id)) {
    		User user = userService.findById(id);
    		if (user != null) {
    			String status = "锁定".equals(open) ? IsOrEnum.SHI.getKey() : IsOrEnum.FOU.getKey();
				user.setStatus(status);
				userService.saveUser(user);
				resultMap.put("result", true);
			} else {
				resultMap.put("result", false);
			}
		} else {
			resultMap.put("result", false);
		}
    	return resultMap;
    }
    
    
}  
