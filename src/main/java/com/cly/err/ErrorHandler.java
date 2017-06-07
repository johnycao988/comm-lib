package com.cly.err;

public interface ErrorHandler {
	
	public String getErrorMessage(String errCode,String... params);
	
	public String getLanguage(); 

}
