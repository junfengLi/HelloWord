package com.web.open.msg;

public class MusicMessage extends BaseMessage {
	private String title;
	private String description;
	private String musicUrl;
	private String hqMusicUrl;
	private String thumbMediaId;

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

	public String getMusicUrl() {
		return musicUrl;
	}

	public void setMusicUrl(String musicUrl) {
		this.musicUrl = musicUrl;
	}

	public String getHqMusicUrl() {
		return hqMusicUrl;
	}

	public void setHqMusicUrl(String hqMusicUrl) {
		this.hqMusicUrl = hqMusicUrl;
	}

	public String getThumbMediaId() {
		return thumbMediaId;
	}

	public void setThumbMediaId(String thumbMediaId) {
		this.thumbMediaId = thumbMediaId;
	}

	@Override
	public String toString() {
		String outXml = "<xml>" + super.toString() + "<Music>" + "<Title><![CDATA[" + title
				+ "]]></Title>" + "<Description><![CDATA[" + description
				+ "]]></Description>" + "<MusicUrl><![CDATA[" + musicUrl
				+ "]]></MusicUrl>" + "<HQMusicUrl><![CDATA[" + hqMusicUrl
				+ "]]></HQMusicUrl>" + "<ThumbMediaId><![CDATA[" + thumbMediaId
				+ "]]></ThumbMediaId>" + "</Music>" + "</xml>";
		return outXml;
	}
}