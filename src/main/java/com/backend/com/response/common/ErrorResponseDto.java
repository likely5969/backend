package com.backend.com.response.common;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import com.backend.com.codes.HttpCodes;

public class ErrorResponseDto  extends ResponseDto {

	public ErrorResponseDto(HttpCodes errorCode) {
		super(false, errorCode.getCode(),errorCode.getMessage(),LocalDateTime.now());
 	}
	
	public ErrorResponseDto(HttpCodes errorCode,Exception ex) {
		super(false, errorCode.getCode(	),errorCode.getMessage(ex.getMessage()),LocalDateTime.now());
 	}
	
	public ErrorResponseDto(HttpCodes errorCode,String message) {
		super(false, errorCode.getCode(),errorCode.getMessage(message),LocalDateTime.now());
 	}
	
	public ErrorResponseDto(HttpCodes errorCode, String message, LocalDateTime now) {
		super(false,errorCode.getCode(),errorCode.getMessage(message),now);
	
	}
 

	public static ErrorResponseDto of(HttpCodes errorCode) {
		return new ErrorResponseDto(errorCode);
	}
	
	public static ErrorResponseDto of(HttpCodes errorCode,Exception e) {
		return new ErrorResponseDto(errorCode,e);
	}
	
	public static ErrorResponseDto of(HttpCodes errorCode,String message) {
		return new ErrorResponseDto(errorCode,message);
	}
	
	
	
	
	
	
 
 
	
	
	
}
