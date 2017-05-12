/*
* @ClassName:Keyword.java
* @Description: TODO desc 
* @author: Lijunfeng
* @date 2017-05-11
*/
package com.web.wechat.pojo;

import com.web.commons.pojo.BasePage;

public class Keyword  extends BasePage {
    private String id;

    private String wechatid;

    private String servicetype;

    private String keyword;
    
    private String issite;
   
	private String messagetype;

    private Long createtime;
    
    private Long createtime1;
    private Long createtime2;
    private String notservicetype;
    

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getWechatid() {
        return wechatid;
    }

    public void setWechatid(String wechatid) {
        this.wechatid = wechatid;
    }

    public String getServicetype() {
        return servicetype;
    }

    public void setServicetype(String servicetype) {
        this.servicetype = servicetype;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    
    public String getIssite() {
		return issite;
	}

	public void setIssite(String issite) {
		this.issite = issite;
	}

	
    public String getMessagetype() {
        return messagetype;
    }

    public void setMessagetype(String messagetype) {
        this.messagetype = messagetype;
    }

    public Long getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Long createtime) {
        this.createtime = createtime;
    }

	public Long getCreatetime1() {
		return createtime1;
	}

	public void setCreatetime1(Long createtime1) {
		this.createtime1 = createtime1;
	}

	public Long getCreatetime2() {
		return createtime2;
	}

	public void setCreatetime2(Long createtime2) {
		this.createtime2 = createtime2;
	}

	public String getNotservicetype() {
		return notservicetype;
	}

	public void setNotservicetype(String notservicetype) {
		this.notservicetype = notservicetype;
	}
    
}