package com.backend.com.exception;

import com.backend.com.codes.HttpCodes;

import lombok.Getter;
@SuppressWarnings("serial")
@Getter
public class GeneralException extends RuntimeException{
	private final HttpCodes errorCode;
	public GeneralException() {
		this.errorCode = null;}
	
	public GeneralException(String exception) {
		super(HttpCodes.INTERNAL_SERVER_ERROR.getMessage());
		this.errorCode = HttpCodes.INTERNAL_SERVER_ERROR;
	}
	
	public GeneralException(String message,Throwable cause) {
		super(HttpCodes.INTERNAL_SERVER_ERROR.getMessage(cause));
		this.errorCode = HttpCodes.INTERNAL_SERVER_ERROR;
	}
	
	
	public GeneralException(Throwable cause) {
		super(HttpCodes.INTERNAL_SERVER_ERROR.getMessage(cause));
		this.errorCode = HttpCodes.INTERNAL_SERVER_ERROR;
	}
	
}
