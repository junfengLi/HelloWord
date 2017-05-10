/*
* @ClassName:User.java
* @Description: TODO desc 
* @author: Lijunfeng
* @date 2017-05-10
*/
package com.web.manage.pojo;

import java.util.List;

import com.web.commons.pojo.BasePage;
import com.web.commons.util.UrlBase64Utils;

public class User extends BasePage {
    private String id;

    private String email;

    private String groupid;

    private String loginip;

    private String loginname;

    private String loginplace;

    private Long logintime;

    private String password;

    private String realname;

    private String status;

    private String mobile;

    private Long createtime;

    private Long updatetime;
    
    private List<Role> roles;

    public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGroupid() {
        return groupid;
    }

    public void setGroupid(String groupid) {
        this.groupid = groupid;
    }

    public String getLoginip() {
        return loginip;
    }

    public void setLoginip(String loginip) {
        this.loginip = loginip;
    }

    public String getLoginname() {
        return loginname;
    }

    public void setLoginname(String loginname) {
        this.loginname = loginname;
    }

    public String getLoginplace() {
        return loginplace;
    }

    public void setLoginplace(String loginplace) {
        this.loginplace = loginplace;
    }

    public Long getLogintime() {
        return logintime;
    }

    public void setLogintime(Long logintime) {
        this.logintime = logintime;
    }

    public String getPassword() {
		return password;
    }

    public void setPassword(String password) {
		this.password = password;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Long getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Long createtime) {
        this.createtime = createtime;
    }

    public Long getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Long updatetime) {
        this.updatetime = updatetime;
    }
}