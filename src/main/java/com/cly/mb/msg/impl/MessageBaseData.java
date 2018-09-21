package com.cly.mb.msg.impl;

public class MessageBaseData {
	
	protected boolean bChange=true;
	
	public void setChangeFlag(boolean bChange) {
		if (!this.bChange)
			return;
		else
			this.bChange = bChange;

	}
	
}
