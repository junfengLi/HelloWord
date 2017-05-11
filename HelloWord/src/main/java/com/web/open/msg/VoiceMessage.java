package com.web.open.msg;

public class VoiceMessage extends BaseMessage {
	private String mediaId;

	public String getMediaId() {
		return this.mediaId;
	}

	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}

	@Override
	public String toString() {
		String outXml = "<xml>" + super.toString()
				+ "<Voice><MediaId><![CDATA[" + mediaId
				+ "]]></MediaId></Voice>" + "</xml>";
		return outXml;
	}

}