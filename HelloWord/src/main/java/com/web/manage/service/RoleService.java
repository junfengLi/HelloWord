package com.web.manage.service;

import java.util.List;
import java.util.Map;

import com.web.commons.jqgrid.UIPage;
import com.web.manage.pojo.Permission;
import com.web.manage.pojo.Role;


public interface RoleService {
	List<Role> getRoles();
	
	UIPage getPage(Map<String, Object> searchParams, int pageNumber, int pageSize);	
	
	List<Permission> getPermissionsByRoleId(String roleId);
}
