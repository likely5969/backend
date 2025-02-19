package com.backend.com.response.common;

import java.time.LocalDateTime;

import com.backend.com.codes.HttpCodes;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ResponseDto {
	private final Boolean isSuccess;
	private final Integer code;
	private final String message;
	private final LocalDateTime now;

	public static ResponseDto of(Boolean success, HttpCodes code) {
	    return new ResponseDto(success, code.getCode(), code.getMessage(""),LocalDateTime.now());  // 빈 문자열로 호출
	}

	public static ResponseDto of(Boolean success, HttpCodes errorCode, Exception ex) {
	    return new ResponseDto(success, errorCode.getCode(), errorCode.getMessage(ex),LocalDateTime.now());  // 예외 객체를 넘김
	}

	public static ResponseDto of(Boolean success, HttpCodes errorCode, String message) {
	    return new ResponseDto(success, errorCode.getCode(), errorCode.getMessage(message), LocalDateTime.now());  // 메시지 문자열을 넘김
	}  
	
	public static ResponseDto of(Boolean success, HttpCodes errorCode, String message,LocalDateTime now) {
	    return new ResponseDto(success, errorCode.getCode(), errorCode.getMessage(message),now);  // 메시지 문자열을 넘김
	}

}
