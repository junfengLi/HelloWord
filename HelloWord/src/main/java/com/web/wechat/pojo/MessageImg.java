/*
* @ClassName:MessageImg.java
* @Description: TODO desc 
* @author: Lijunfeng
* @date 2017-05-11
*/
package com.web.wechat.pojo;

import com.web.commons.pojo.BasePage;

public class MessageImg  extends BasePage {
    private String id;

    private String title;

    private String description;

    private String image;

    private Long createtime;

    private Long updatetime;

    private String demo;

    private String seenum;

    private String issite;

    private String ismenu;

    private String ismessage;

    private String keywordid;

    private String menuuid;

    private String wechatid;

    private String seturl;

    private String imgsort;

    private String content;
    
    private String menuUidLike;
    private Long updatetime1;
    private Long updatetime2;

    public String getMenuUidLike() {
		return menuUidLike;
	}

	public void setMenuUidLike(String menuUidLike) {
		this.menuUidLike = menuUidLike;
	}

	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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

    public String getDemo() {
        return demo;
    }

    public void setDemo(String demo) {
        this.demo = demo;
    }

    public String getSeenum() {
        return seenum;
    }

    public void setSeenum(String seenum) {
        this.seenum = seenum;
    }

    public String getIssite() {
        return issite;
    }

    public void setIssite(String issite) {
        this.issite = issite;
    }

    public String getIsmenu() {
        return ismenu;
    }

    public void setIsmenu(String ismenu) {
        this.ismenu = ismenu;
    }

    public String getIsmessage() {
        return ismessage;
    }

    public void setIsmessage(String ismessage) {
        this.ismessage = ismessage;
    }

    public String getKeywordid() {
        return keywordid;
    }

    public void setKeywordid(String keywordid) {
        this.keywordid = keywordid;
    }

    public String getMenuuid() {
        return menuuid;
    }

    public void setMenuuid(String menuuid) {
        this.menuuid = menuuid;
    }

    public String getWechatid() {
        return wechatid;
    }

    public void setWechatid(String wechatid) {
        this.wechatid = wechatid;
    }

    public String getSeturl() {
        return seturl;
    }

    public void setSeturl(String seturl) {
        this.seturl = seturl;
    }

    public String getImgsort() {
        return imgsort;
    }

    public void setImgsort(String imgsort) {
        this.imgsort = imgsort;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

	public Long getUpdatetime1() {
		return updatetime1;
	}

	public void setUpdatetime1(Long updatetime1) {
		this.updatetime1 = updatetime1;
	}

	public Long getUpdatetime2() {
		return updatetime2;
	}

	public void setUpdatetime2(Long updatetime2) {
		this.updatetime2 = updatetime2;
	}
    
}