package com.web.commons.shiro;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import com.web.manage.pojo.User;

/**** 
 * 自定义Realm 
 *  
 * @author Swinglife 
 *  
 */  
public class MyShiroRealm extends AuthorizingRealm {  
  
	/**用户的业务类**/  
    private AuthService authService;  
      
    public AuthService getAuthService() {  
        return authService;  
    }  
  
    public void setAuthService(AuthService authService) {  
        this.authService = authService;  
    }  
	
    
    /*** 
     * 获取授权信息 
     */  
    @Override  
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection pc) {  
    	Account account = (Account) pc.getPrimaryPrincipal();
        String username = account.getLoginName();
        if (username != null) {  
            List<String> pers = authService.getPermissionsByUserName(username);  
            List<String> roles = authService.getRolesByUserName(username);  
            if (CollectionUtils.isNotEmpty(roles) && CollectionUtils.isNotEmpty(pers)) {  
                SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();  
                for (String role : roles) {  
                    //将角色添加到用户权限信息中  
                    info.addRole(role); 
                }  
                for (String per : pers) {  
                    //将资源添加到用户权限信息中  
                    info.addStringPermission(per);  
                }  
                return info;  
            }  
        }  
        return null;  
    }  
    
    /*** 
     * 获取认证信息 
     */  
    @Override  
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken at) throws AuthenticationException {  
        UsernamePasswordToken token = (UsernamePasswordToken) at;  
        // 通过表单接收的用户名  
        String username = token.getUsername();  
        if (username != null && !"".equals(username)) {  
            User user = authService.getUserByUserName(username);  
            if (user != null) {  
            	Account account = new Account(user.getLoginname(), new String(
						token.getPassword()), "realName:" + user.getLoginname(), "");
                return new SimpleAuthenticationInfo(account, user.getPassword(), user.getLoginname());  
            }  
        }  
        return null;  
    }  
      
}
