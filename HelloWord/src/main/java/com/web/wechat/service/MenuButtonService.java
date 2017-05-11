package com.web.wechat.service;


import java.util.List;

import com.web.wechat.pojo.MenuButton;


public interface MenuButtonService {
	
	public MenuButton saveMenuButton(MenuButton menuButton);
	public MenuButton findMenuButtonById(String id);
	public boolean deleteById(String id);
	
	public List<MenuButton> findFZByWechatId(String wechatId);
	public List<MenuButton> findSubMenuByPid(String pid);
}
