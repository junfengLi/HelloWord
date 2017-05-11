/*
* @ClassName:MenuButton.java
* @Description: TODO desc 
* @author: Lijunfeng
* @date 2017-05-11
*/
package com.web.wechat.pojo;

import com.web.commons.pojo.BasePage;

public class MenuButton  extends BasePage{
    private String id;

    private String wechatid;

    private String pid;

    private String buttontype;

    private String name;

    private String menukey;

    private String url;

    private String buttonsort;

    private String isoauth;

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

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getButtontype() {
        return buttontype;
    }

    public void setButtontype(String buttontype) {
        this.buttontype = buttontype;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMenukey() {
        return menukey;
    }

    public void setMenukey(String menukey) {
        this.menukey = menukey;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getButtonsort() {
        return buttonsort;
    }

    public void setButtonsort(String buttonsort) {
        this.buttonsort = buttonsort;
    }

    public String getIsoauth() {
        return isoauth;
    }

    public void setIsoauth(String isoauth) {
        this.isoauth = isoauth;
    }
}