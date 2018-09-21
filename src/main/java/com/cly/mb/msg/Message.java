package com.cly.mb.msg;

public interface Message {
	
	public final static int MT_RESPONSE_MESSAGE=2;
	public final static int MT_REQUEST_MESSAGE=1;
	
	public MessageHeader getMessageHeader();

	public MessagePayload  getMessagePayload();
}
