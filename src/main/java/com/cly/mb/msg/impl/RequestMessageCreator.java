package com.cly.mb.msg.impl;

import com.cly.mb.msg.Message;

public class RequestMessageCreator extends MessageCreator {
	
	public RequestMessageCreator(){
		super();
		this.setMessageType(Message.MT_REQUEST_MESSAGE);
	}

}
