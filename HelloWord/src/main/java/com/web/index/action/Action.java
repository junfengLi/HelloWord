package com.web.index.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class Action {  
	
	
	private static final String BASE_PATH = "/index/";
    @RequestMapping({"/index"})  
    public String index() {  
    	 return BASE_PATH + "index";  
    }
    @RequestMapping("/index/{module}/forward")
    public String forward(@PathVariable("module")String module,
    		Model model,HttpServletRequest request,HttpServletResponse response) {  
    	
        return BASE_PATH + module;  
    }
    @RequestMapping("/index/{module}/load")
    @ResponseBody
    public ModelAndView load(@PathVariable("module")String module, 
    		Model model,HttpServletRequest request,HttpServletResponse response) {  
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName(BASE_PATH + module);
		modelAndView.addObject(model);
		
		return modelAndView;
	}
}  
