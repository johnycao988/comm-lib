package com.cly.mb.msg.impl;

import com.cly.comm.util.JSONUtil;
import com.cly.mb.msg.MessageHeader;
import com.cly.mb.msg.MessageInfo;
import com.cly.mb.msg.MessageProperties;
import com.cly.mb.msg.ServiceInfo;
import com.cly.mb.msg.UserInfo;

import net.sf.json.JSONObject;

public class MessageHeaderImpl implements MessageHeader {

	private JSONObject jsonMsgHeader;

	private MessageInfoImpl msgInfo;

	public MessageHeaderImpl(JSONObject jsonMsgHeader, boolean bChange) {

		this.jsonMsgHeader = jsonMsgHeader;
		msgInfo = new MessageInfoImpl(JSONUtil.getJSONObject(this.jsonMsgHeader, MessageInfo.MSGINFO), bChange);
	}

	public void setChangeFlag(boolean bChange) {
		this.msgInfo.setChangeFlag(bChange);
	}

	@Override
	public MessageInfo getMessageInfo() {

		return this.msgInfo;
	}

	@Override
	public MessageProperties getMessageProperties() {

		return null;
	}

	@Override
	public ServiceInfo getServiceInfo() {

		return null;
	}

	@Override
	public UserInfo getUserInfo() {

		return null;
	}

}
