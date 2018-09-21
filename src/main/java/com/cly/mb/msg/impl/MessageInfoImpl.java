package com.cly.mb.msg.impl;

import com.cly.comm.util.JSONUtil;
import com.cly.mb.msg.MessageInfo;

import net.sf.json.JSONObject;

public class MessageInfoImpl extends MessageInfoData implements MessageInfo {

	private JSONObject jsonMsgInfo = null;
 
	public MessageInfoImpl(JSONObject jsonMsgInfo, boolean bChange) {

		this.bChange = bChange;
		this.jsonMsgInfo = jsonMsgInfo;

	}

	@Override
	public String getMessageId() {

		return JSONUtil.getString(jsonMsgInfo, MSGINFO_ID);
	}

	@Override
	public String getMessageCorrelationId() {

		return JSONUtil.getString(jsonMsgInfo, MSGINFO__CORRELATION_ID);
	}

	@Override
	public long getMessageTimestamp() {
		return JSONUtil.getLong(jsonMsgInfo, MSGINFO__EXPIRATION_SECONDS);
	}

	@Override
	public int getMessageExpiredSeconds() {

		return JSONUtil.getInt(jsonMsgInfo, MSGINFO__TIMESTAMP);
	}

	@Override
	public int getMessageType() {

		return JSONUtil.getInt(jsonMsgInfo, MSGINFO__TYPE);
	}

	@Override
	public void setMessageId(String id) {

		if (!bChange)
			return;

		this.jsonMsgInfo.put(MSGINFO_ID, id);
	}

	@Override
	public void setMessageCorrelationId(String corrId) {

		if (!bChange)
			return;

		this.jsonMsgInfo.put(MSGINFO__CORRELATION_ID, corrId);
	}

	@Override
	public void setMessageTimestamp(long time) {

		if (!bChange)
			return;
		this.jsonMsgInfo.put(MSGINFO__TIMESTAMP, time);

	}

	@Override
	public void setMessageExpiredSeconds(int expirationSeconds) {

		if (!bChange)
			return;
		this.jsonMsgInfo.put(MSGINFO__EXPIRATION_SECONDS, expirationSeconds);
	}

	@Override
	public void setMessageType(int type) {

		if (!bChange)
			return;
		this.jsonMsgInfo.put(MSGINFO__TYPE, type);
	}

}
