package com.web.manage.service;

import java.util.List;
import java.util.Map;

import com.web.commons.jqgrid.UIPage;
import com.web.manage.pojo.Permission;

public interface PermissionService {

	List<Permission> getPermissions();
	
	UIPage getPage(Map<String, Object> searchParams, int pageNumber, int pageSize);	
	
	Permission save(Permission permission);
}
