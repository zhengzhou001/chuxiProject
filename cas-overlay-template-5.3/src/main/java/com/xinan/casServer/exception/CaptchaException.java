package com.xinan.casServer.exception;
 
import javax.security.auth.login.AccountException;
 

public class CaptchaException extends AccountException {
	public CaptchaException() {
		super();
		// TODO Auto-generated constructor stub
	}
 
	public CaptchaException(String msg) {
		super(msg);
		// TODO Auto-generated constructor stub
	}
 
}