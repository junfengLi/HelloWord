package com.web.commons.shiro;

import java.util.List;

import com.web.manage.pojo.User;

public interface AuthService {

	/**** 
     * 通过用户名获取用户对象 
     *  
     * @param username 
     * @return 
     */  
	public User getUserByUserName(String loginName);
  
    /*** 
     * 通过用户名获取权限资源 
     *  
     * @param username 
     * @return 
     */  
    public List<String> getPermissionsByUserName(String username);
    
    /*** 
     * 通过用户名获取角色资源 
     *  
     * @param username 
     * @return 
     */  
    public List<String> getRolesByUserName(String username);
   
}
