/*
* @ClassName:Role.java
* @Description: TODO desc 
* @author: Lijunfeng
* @date 2017-03-17
*/
package com.web.manage.pojo;

import java.util.List;

public class Role {
    private String id;

    private String name;

    private String code;

    private Long createtime;
    
    private List<Permission> permissions;
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Long getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Long createtime) {
        this.createtime = createtime;
    }

	public List<Permission> getPermissions() {
		return permissions;
	}

	public void setPermissions(List<Permission> permissions) {
		this.permissions = permissions;
	}
    
}