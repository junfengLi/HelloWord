package com.web.open.msg;

/**
 * ClassName:BaseMessage 基础消息
 * Function: TODO ADD FUNCTION
 * @Date	 2015	2015年8月28日		下午3:50:14
 * @see
 */
public class BaseMessage {
	protected String toUserName;
	protected String fromUserName;
	protected long createTime;
	protected String msgType;
	protected int funcFlag;

	public BaseMessage() {
	}

	public BaseMessage(String msgType) {
		this.msgType = msgType;
	}

	public String getToUserName() {
		return toUserName;
	}

	public void setToUserName(String toUserName) {
		this.toUserName = toUserName;
	}

	public String getFromUserName() {
		return fromUserName;
	}

	public void setFromUserName(String fromUserName) {
		this.fromUserName = fromUserName;
	}

	public long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}

	public String getMsgType() {
		return msgType;
	}

	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}

	public int getFuncFlag() {
		return funcFlag;
	}

	public void setFuncFlag(int funcFlag) {
		this.funcFlag = funcFlag;
	}
	
	@Override
	public String toString() {
		String outXml = "<ToUserName><![CDATA[" + toUserName
				+ "]]></ToUserName>" + "<FromUserName><![CDATA[" + fromUserName
				+ "]]></FromUserName>" + "<CreateTime>" + createTime
				+ "</CreateTime>" + "<MsgType><![CDATA[" + msgType
				+ "]]></MsgType>";
		return outXml;
	}

}