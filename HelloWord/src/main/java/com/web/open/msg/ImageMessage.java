package com.web.open.msg;

public class ImageMessage extends BaseMessage {
	private String mediaId;

	public String getPicUrl() {
		return this.mediaId;
	}

	public void setPicUrl(String picUrl) {
		this.mediaId = picUrl;
	}

	@Override
	public String toString() {
		String outXml = "<xml>" + super.toString() + "<Image><MediaId><![CDATA[" + mediaId
				+ "]]></MediaId></Image>" + "</xml>";
		return outXml;
	}
}