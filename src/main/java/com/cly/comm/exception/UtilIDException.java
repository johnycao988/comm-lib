package com.cly.comm.exception;

public class UtilIDException extends Exception {
	
	Exception e;

	public UtilIDException(Exception e) {
		// TODO Auto-generated constructor stub
		this.e=e;
		
	}

	public String getMessage(){
		return e.getMessage();
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
