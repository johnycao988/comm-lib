package com.cly.mb.msg;

public interface MessageInfo {
	

	public final static String MSGINFO_ID = "id";
	public final static String MSGINFO__CORRELATION_ID = "corr_id";
	public final static String MSGINFO__EXPIRATION_SECONDS = "expiration";
	public final static String MSGINFO__TIMESTAMP = "timestamp";
	public final static String MSGINFO__TYPE = "type";	
	public final static String MSGINFO = "msgInfo";

	public String getMessageId(); 
	public String getMessageCorrelationId(); 
	public long getMessageTimestamp();
	public int getMessageExpiredSeconds();
	public int getMessageType();	
	 
}
