/*
* @ClassName:Accessory.java
* @Description: TODO desc 
* @author: Lijunfeng
* @date 2017-05-11
*/
package com.web.upload.pojo;

import com.web.commons.pojo.BasePage;

public class Accessory  extends BasePage {
    private String id;

    private Long createtime;

    private String filesize;

    private String linkid;

    private String minitype;

    private String module;

    private String name;

    private String text1;

    private String text2;

    private String url;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Long createtime) {
        this.createtime = createtime;
    }

    public String getFilesize() {
        return filesize;
    }

    public void setFilesize(String filesize) {
        this.filesize = filesize;
    }

    public String getLinkid() {
        return linkid;
    }

    public void setLinkid(String linkid) {
        this.linkid = linkid;
    }

    public String getMinitype() {
        return minitype;
    }

    public void setMinitype(String minitype) {
        this.minitype = minitype;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText1() {
        return text1;
    }

    public void setText1(String text1) {
        this.text1 = text1;
    }

    public String getText2() {
        return text2;
    }

    public void setText2(String text2) {
        this.text2 = text2;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}