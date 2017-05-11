package com.web.wechat.action;

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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.web.commons.jqgrid.UINode;
import com.web.commons.util.ConfigUtil;
import com.web.open.menu.EventButton;
import com.web.open.menu.Menu;
import com.web.open.support.AccessToken;
import com.web.open.util.WechatUtil;
import com.web.wechat.pojo.MenuButton;
import com.web.wechat.service.MenuButtonService;
import com.web.wechat.service.WechatService;

@Controller
@RequestMapping("/user/menu")
public class MenuButtonAction {
	@Autowired
	private WechatService wechatService;
	@Autowired
	private MenuButtonService menuButtonService;

	
	private final static String BASE_PATH = "/user/menu/";
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
		
		if ("list".equals(module)) {
			List<EventButton> buttons = new ArrayList<EventButton>();
			setListModel(buttons, wechatId);
			model.addAttribute("buttons", buttons);
		} else if ("edit".equals(module)){
			String id = request.getParameter("id");
			MenuButton menuButton = menuButtonService.findMenuButtonById(id);
			model.addAttribute("button", menuButton);
			model.addAttribute("wechatId", menuButton.getWechatid());
			module = "add";
		}
		return BASE_PATH+module;
	}
	
	
	

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> save(MenuButton menubutton, HttpServletRequest request){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		menuButtonService.saveMenuButton(menubutton);
		resultMap.put("success", true);
		return resultMap;
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> delete(@RequestParam(value="id", defaultValue = "")String id, HttpServletRequest request){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		menuButtonService.deleteById(id);
		resultMap.put("success", true);
		return resultMap;
	}
	
	
	@RequestMapping(value = "/creatMenu", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> creatMenu(@RequestParam(value="wechatId", defaultValue="")String wechatId, HttpServletRequest request){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<EventButton> buttons = new ArrayList<EventButton>();
		setListModel(buttons, wechatId);
		Menu menu = new Menu();
		menu.setButton(buttons);
		AccessToken accessToken = WechatUtil.getToken(wechatId);
		if (accessToken != null) {
			String token = accessToken.getToken();
			
			String error = WechatUtil.createMenu(menu, token);
			if(!"0".equals(error)){
				resultMap.put("success", false);
				resultMap.put("desc", "****返回结果异常："+error);
			}
			resultMap.put("success", true);
		} else {
			resultMap.put("success", false);
			resultMap.put("desc", "获取信息失败，请刷新后重试");
		}
		
		
		return resultMap;
	}
	
	@RequestMapping(value = "/selectCombo")
	@ResponseBody
	public List<UINode> selectCombo(@RequestParam(value = "id", defaultValue = "") String id,
			@RequestParam(value = "wechatId", defaultValue = "") String wechatId){
		List<UINode> UINodes = new ArrayList<UINode>();
		List<MenuButton> menuButtons = menuButtonService.findFZByWechatId(wechatId);
		if (CollectionUtils.isNotEmpty(menuButtons)) {
			if (StringUtils.isNotBlank(id)) {
				UINodes.add(new UINode(ConfigUtil.MENU_YJCD, "一级菜单"));
			} else {
				if (menuButtons.size()<3) {
					UINodes.add(new UINode(ConfigUtil.MENU_YJCD, "一级菜单"));
				}	
			}
		} else {
			UINodes.add(new UINode(ConfigUtil.MENU_YJCD, "一级菜单"));
		}
			
		for (MenuButton menuButton : menuButtons) {
			UINodes.add(new UINode(menuButton.getId(), menuButton.getName()));
		}
		
		return UINodes;
	}
	
	
	
	
	/*************************************私有方法*************************************/
	
	
	/**获取菜单信息*/
	private void setListModel(List<EventButton> buttons, String wechatId) {
		List<MenuButton> menuButtons = menuButtonService.findFZByWechatId(wechatId);
		for (MenuButton menuButton : menuButtons) {
			EventButton button = new EventButton(menuButton.getName());
			button.setId(menuButton.getId());
			List<MenuButton> subMenuButtons = menuButtonService.findSubMenuByPid(menuButton.getId());
			if (CollectionUtils.isNotEmpty(subMenuButtons)) {
				for (MenuButton subMenuButton : subMenuButtons) {
					EventButton subButton = new EventButton(subMenuButton.getName());
					subButton.setId(subMenuButton.getId());
					setMenu(subButton, subMenuButton);
					button.addButton(subButton);
				}
			} else {
				setMenu(button, menuButton);
			}
			buttons.add(button);
		}
	}
	/**设置相应菜单*/
	private void setMenu(EventButton button, MenuButton menuButton){
		button.setType(menuButton.getButtontype());
		if (ConfigUtil.MENU_EVENT_VIEW.equals(menuButton.getButtontype())) {
			button.setUrl(menuButton.getUrl());
		} else {
			button.setKey(menuButton.getMenukey());
		}
	}

	
}
