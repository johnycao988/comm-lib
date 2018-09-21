package com.cly.mb.msg.impl;

import com.cly.mb.msg.Message;

public class ResponseMessageCreator extends MessageCreator  {

	public  ResponseMessageCreator(){
		super();
		this.setMessageType(Message.MT_RESPONSE_MESSAGE);
	} 
	
}
