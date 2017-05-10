package com.web.manage.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.web.commons.jqgrid.UIPage;

@RequestMapping("/role")  
@Controller
public class RoleAction {  
	
	
	private static final String BASE_PATH = "/manage/sys/role/";

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
    		Model model,HttpServletRequest request,HttpServletResponse response) {  
    	
        return BASE_PATH + module;  
    }
    
    
    
    
    
    
    
    
    
    
    
    //--------------------------------------异步数据---------------------------------------------------
    @RequestMapping("/getPage")
    @ResponseBody
    public UIPage getPage(@RequestParam(value="page",defaultValue="1")int pageNum,
			@RequestParam(value="rows",defaultValue="10")int pageSize,
			HttpServletRequest request){
    	UIPage page = new UIPage();
    	List<Map<String,Object>> rows=new ArrayList<Map<String,Object>>();
//    	for (User user : users) {
//    		Map<String,String> row=new HashMap<String, String>();
//    		row.put("id", user.getId());
//    		row.put("name", user.getName());
//    		rows.add(row);
//    		rows.add(row);
//    		rows.add(row);
//    		rows.add(row);
//		}
    	for (int i = Integer.valueOf(pageNum) - 1 ; i < Integer.valueOf(pageNum) *pageSize ; i++) {
    		Map<String,Object> row=new HashMap<String, Object>();
    		row.put("id", i);
    		row.put("name", "name" + i);
    		rows.add(row);
		}
    	page.setRows(rows);
    	page.setRecords(20);
    	return page;
    }
}  
