package com.cly.mb.msg.impl;

public abstract class MessageInfoData extends MessageBaseData{

	public abstract void setMessageId(String id); 
	public abstract void setMessageCorrelationId(String corrId); 
	public abstract void setMessageTimestamp(long time);
	public abstract void setMessageExpiredSeconds(int expirationSeconds);
	public abstract void setMessageType(int type);
	
}
