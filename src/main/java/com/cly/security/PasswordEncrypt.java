package com.cly.security;

public interface PasswordEncrypt {

	public String encrypt (String pwd) throws SecurityAuthException;
	
}
