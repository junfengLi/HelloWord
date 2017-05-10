package com.web.manage.service;

import java.util.List;
import java.util.Map;

import com.web.commons.jqgrid.UIPage;
import com.web.manage.pojo.Role;
import com.web.manage.pojo.User;


public interface UserService {
	User findById(String id);
	void saveUser(User user);
	void deleteUserById(String id);
	public User findByLoginName(String loginName);
	UIPage getPage(User user, int pageNumber, int pageSize);
	List<Role> getRolesByLoginName(String loginName);
	
}
