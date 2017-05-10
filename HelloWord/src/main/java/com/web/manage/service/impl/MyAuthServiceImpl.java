package com.web.manage.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.web.commons.util.IsOrEnum;
import com.web.manage.dao.UserDao;
import com.web.manage.pojo.Permission;
import com.web.manage.pojo.Role;
import com.web.manage.pojo.User;
import com.web.manage.service.MyAuthService;

public class MyAuthServiceImpl implements MyAuthService {

	@Autowired 
	private UserDao userDao;

	@Override
	public User getUserByUserName(String loginName) {  
		User user = new User();
		user.setLoginname(loginName);
		user.setStatus(IsOrEnum.FOU.getKey());
		List<User> users = userDao.selectByStatement(user);
		if (CollectionUtils.isNotEmpty(users)) {
			return users.get(0);
		} else {
			return null;
		} 
	}  
	@Override
    public List<String> getPermissionsByUserName(String loginName) {  
		User user = getUserByUserName(loginName);
		List<Role> roles= user.getRoles();
		List<String> list = new ArrayList<>();
        List<String> permissionList = new ArrayList<>();
        for (Role role : roles) {
        	List<Permission> permissions = role.getPermissions();
        	for (Permission permission : permissions) {
        		list.add(permission.getCode());
        	}
        	permissionList.addAll(list);
        }
        return permissionList;  
    }
	@Override
	public List<String> getRolesByUserName(String loginName) {
		User user = getUserByUserName(loginName);
		List<Role> roles= user.getRoles();
		List<String> list = new ArrayList<>();
		 for (Role role : roles) {
			list.add(role.getCode());
		}
		return list;
	}  

}
