package com.cly.mb.msg;

public interface MessageHeader {
	
	public MessageInfo getMessageInfo();

	public MessageProperties getMessageProperties();
	
	public ServiceInfo getServiceInfo();
	
	public UserInfo getUserInfo();
	
}
