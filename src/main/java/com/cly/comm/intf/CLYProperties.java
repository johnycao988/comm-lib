package com.cly.comm.intf;
 

public abstract interface CLYProperties{ 
	
	public abstract String getProperty(String key);
	
	public abstract void setProperty(String key, String value);

}
