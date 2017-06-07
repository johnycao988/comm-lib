package com.cly.err;

public class CLYException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String errCode;

	public CLYException(String errMsg) {
		super(errMsg);
	}

	public CLYException(Exception e) {
		super(e);
	}

	public CLYException(String errCode, String errMsg) {
		super(errMsg);
		this.errCode = errCode;
	}

	public String getErrorCode() {
		return this.errCode;
	}


}
