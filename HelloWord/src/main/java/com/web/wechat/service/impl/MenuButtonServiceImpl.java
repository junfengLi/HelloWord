package com.web.wechat.service.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.web.commons.util.ConfigUtil;
import com.web.wechat.dao.MenuButtonDao;
import com.web.wechat.pojo.MenuButton;
import com.web.wechat.service.MenuButtonService;


@Service("menuButtonService")
@Transactional(propagation = Propagation.NOT_SUPPORTED,readOnly=true)
public class MenuButtonServiceImpl  implements MenuButtonService {
	@Autowired
	private MenuButtonDao menuButtonDao;

	@Override
	public MenuButton saveMenuButton(MenuButton menuButton) {
		if (StringUtils.isBlank(menuButton.getId())) {
			menuButtonDao.insertSelective(menuButton);
		} else {
			menuButtonDao.updateByPrimaryKeySelective(menuButton);
		}
		return menuButton;
	}

	@Override
	public MenuButton findMenuButtonById(String id) {
		return menuButtonDao.selectByPrimaryKey(id);
	}


	@Override
	public boolean deleteById(String id) {
		MenuButton menuButton = menuButtonDao.selectByPrimaryKey(id);
		if (ConfigUtil.MENU_YJCD.equals(menuButton.getPid())) {
			List<MenuButton> suButtons = findSubMenuByPid(id);
			for (MenuButton menuButton2 : suButtons) {
				deleteById(menuButton2.getId());
			}
		}
		menuButtonDao.deleteByPrimaryKey(id);
		return true;
	}

	@Override
	public List<MenuButton> findFZByWechatId(String wechatId) {
		MenuButton record = new MenuButton();
		record.setWechatid(wechatId);
		record.setPid(ConfigUtil.MENU_YJCD);
		return menuButtonDao.selectByStatement(record);
	}

	@Override
	public List<MenuButton> findSubMenuByPid(String pid) {
		MenuButton record = new MenuButton();
		record.setPid(pid);
		return menuButtonDao.selectByStatement(record);
	}

	
	
}
