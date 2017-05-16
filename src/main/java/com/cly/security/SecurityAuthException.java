package com.cly.security;

public class SecurityAuthException extends Exception {

	/**
	 * 
	 */
	private String errCode;
	private String errMsg;
	private static final long serialVersionUID = 1L;

	public SecurityAuthException(Exception e, String errCode, String errMsg) {
		super(e);
		init(errCode, errMsg);
	}

	private void init(String errCode, String errMsg) {

		this.errCode = errCode;
		this.errMsg = errMsg;

	}

	public SecurityAuthException(String errCode, String errMsg) {
		init(errCode, errMsg);
	}

	public String getErrCode() {
		return errCode;
	}

	@Override
	public String getMessage() {
		return errMsg;
	}

}
