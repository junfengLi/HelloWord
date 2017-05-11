package com.web.open.msg;

import org.apache.commons.lang.StringUtils;

/**
 * 
 * ClassName:Article 图文对象 Function: TODO ADD FUNCTION
 * @Date 2015 2015年8月28日 下午3:50:53
 * @see
 */
public class Article {
	private String title;
	private String description;
	private String picUrl;
	private String url;

	public Article(String title, String description, String picUrl, String url) {
		this.title = title;
		this.description = description;
		this.picUrl = picUrl;
		this.url = url;
	}

	public Article() {
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

	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		String xml = "<item>" + "<Title><![CDATA[" + title + "]]></Title> ";
		if (StringUtils.isNotBlank(description)) {
			xml += "<Description><![CDATA[" + description + "]]></Description>";
		}
		if (StringUtils.isNotBlank(picUrl)) {
			xml += "<PicUrl><![CDATA[" + picUrl + "]]></PicUrl>";
		}
			xml += "<Url><![CDATA[" + url + "]]></Url>" + "</item>";
		return xml;
	}

}