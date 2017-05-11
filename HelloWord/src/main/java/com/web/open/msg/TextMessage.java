package com.web.open.msg;

/**
 * ClassName:TextMessage 文本消息
 * Function: TODO ADD FUNCTION
 * @Date	 2015	2015年8月28日		下午3:50:04
 * @see
 */
public class TextMessage extends BaseMessage {
	private String content;

	public TextMessage() {
		super("text");
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		String outXml = "<xml>" + super.toString() + "<Content><![CDATA[" + content
				+ "]]></Content>" + "</xml>";
		return outXml;
	}
}