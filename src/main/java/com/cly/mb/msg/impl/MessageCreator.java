package com.cly.mb.msg.impl;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.UUID;

import com.cly.mb.msg.Message;
import com.cly.mb.msg.MessageHeader;
import com.cly.mb.msg.MessageInfo;
import com.cly.mb.msg.MessagePayload;

import net.sf.json.JSONObject;

public class MessageCreator extends MessageInfoData {

	private final static String MSG_HEADER = "header";	
	
	private final static String MSG_PAYLOAD = "payload";
	

	private final JSONObject jsonMsgInfo = new JSONObject();

	private final JSONObject jsonUserInfo = new JSONObject();

	private final JSONObject jsonServiceInfo = new JSONObject();

	private final JSONObject jsonPropInfo = new JSONObject();

	public MessageCreator() {

		String msgId = UUID.randomUUID().toString();
		this.setMessageId(msgId);
		this.setMessageCorrelationId("");
		this.setMessageExpiredSeconds(-1);

	}

	public void setMessageId(String id) {

		jsonMsgInfo.put(MessageInfo.MSGINFO_ID, id);
	}

	public void setMessageCorrelationId(String id) {

		jsonMsgInfo.put(MessageInfo.MSGINFO__CORRELATION_ID, id);
	}

	public void setMessageExpiredSeconds(int seconds) {

		jsonMsgInfo.put(MessageInfo.MSGINFO__EXPIRATION_SECONDS, seconds);
	}
	
	private void setMessageTimestamp() {

		Calendar cal= new GregorianCalendar();
		
		long time=cal.getTimeInMillis();
		
		jsonMsgInfo.put(MessageInfo.MSGINFO__TIMESTAMP, time);
	}
	
	public void setMessageType(int type){
		jsonMsgInfo.put(MessageInfo.MSGINFO__TYPE, type);
	}
	
	public String getMessage(){
		
		
		JSONObject jsonMsg=new JSONObject();
		JSONObject jsonMsgHeader=new JSONObject();
		
		this.setMessageTimestamp();
		
		jsonMsgHeader.put(MessageInfo.MSGINFO,this.jsonMsgInfo);
		
		JSONObject jsonPayload=new JSONObject();
		
		jsonMsg.put(MSG_HEADER,jsonMsgHeader);
		jsonMsg.put(MSG_PAYLOAD,jsonPayload);
		
		
		return jsonMsg.toString();
	}

	@Override
	public void setMessageTimestamp(long time) {
		// TODO Auto-generated method stub
		
	}
}

class _MessageImpl implements Message{

	public _MessageImpl (JSONObject jsonMsgHeader, JSONObject jsonMsgPayload){
		
	}
	
	@Override
	public MessageHeader getMessageHeader() {
	 
		return null;
	}

	@Override
	public MessagePayload getMessagePayload() {
	 
		return null;
	}
	
}
