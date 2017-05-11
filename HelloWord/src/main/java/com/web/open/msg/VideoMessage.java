package com.web.open.msg;

public class VideoMessage extends BaseMessage{
	private String mediaId;
	private String title;
	private String description;
	public String getMediaId() {
		return mediaId;
	}
	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
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
	
	 @Override
		public String toString() {
		 String outXml = "<xml>"
				 	+ super.toString() 
				 	+ "<Video>"
					+ "<MediaId><![CDATA[" + mediaId + "]]></MediaId>"
					+ "<Title><![CDATA[" + title + "]]></Title>"
					+ "<Description><![CDATA[" + description + "]]></Description>"
					+ "</Video>"
					+ "</xml>";
		 return outXml;
		}

	
}
