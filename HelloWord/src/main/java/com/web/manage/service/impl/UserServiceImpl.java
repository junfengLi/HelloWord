package com.web.manage.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.web.commons.jqgrid.UIPage;
import com.web.commons.util.BeanCopyUtil;
import com.web.commons.util.DateUtil;
import com.web.commons.util.IsOrEnum;
import com.web.commons.util.UrlBase64Utils;
import com.web.manage.dao.UserDao;
import com.web.manage.pojo.Role;
import com.web.manage.pojo.User;
import com.web.manage.service.UserService;

@Component("userService")
@Transactional  
public class UserServiceImpl implements UserService {
	@Autowired 
	private UserDao userDao;

	@Override
	public User findById(String id) {
		return userDao.selectByPrimaryKey(id);
	}

	@Override
	public void saveUser(User user) {
		if (StringUtils.isBlank(user.getStatus())) {
			user.setStatus(IsOrEnum.FOU.getKey());
		}
		user.setUpdatetime(System.currentTimeMillis());
		if (StringUtils.isBlank(user.getId())) {
			user.setCreatetime(System.currentTimeMillis());
			userDao.insertSelective(user);
		} else {
			userDao.updateByPrimaryKeySelective(user);
		}
	}

	@Override
	public void deleteUserById(String id) {
		userDao.deleteByPrimaryKey(id);
	}
	

	@Override
	public User findByLoginName(String loginName) {
		User user = new User();
		user.setLoginname(loginName);
		List<User> users = userDao.selectByStatement(user);
		if (CollectionUtils.isNotEmpty(users) && users.size() != 0) {
			return users.get(0);
		} else {
			return null;
		}
	}

	@Override
	public UIPage getPage(User user, int pageNumber, int pageSize) {
		UIPage page = new UIPage(String.valueOf(pageSize));
		List<Map<String,Object>> rows=new ArrayList<Map<String,Object>>();
		long count = userDao.selectByStatementCount(user);
		user.setRows(pageSize);
		user.setOffset(pageNumber);
		List<User> users = userDao.selectByStatement(user);
		for (User user2 : users) {
			@SuppressWarnings("unchecked")
			Map<String,Object> row=BeanCopyUtil.CopyBeanToMap(user2);
			row.put("createtime", DateUtil.getFormatDateTime(user2.getCreatetime()));
    		rows.add(row);
		}
		page.setRows(rows);
    	page.setRecords(count -1);
    	return page;
	}

	@Override
	public List<Role> getRolesByLoginName(String loginName) {
		User user = findByLoginName(loginName);
		if (user != null) {
			return user.getRoles();
		} else {
			return null;
		}
	}


	

}
