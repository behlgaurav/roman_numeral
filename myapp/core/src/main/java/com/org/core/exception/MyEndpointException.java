package com.org.core.exception;

public class MyEndpointException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String code;
	private String message;

	public MyEndpointException(String code,String message) {
		this.code = code;
		this.message = message;
	}

	public String getCode() {
		return code;
	}

	@Override
	public String getMessage() {
		return message;
	}

	@Override
	public String toString() {
		return "MyEndpointException{" + "code='" + code + '\'' + ", message='" + message + '\'' + '}';
	}
}
