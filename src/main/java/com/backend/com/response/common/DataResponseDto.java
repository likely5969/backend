package com.backend.com.response.common;

import java.time.LocalDateTime;

import com.backend.com.codes.HttpCodes;

import lombok.Getter;

@Getter
 public class DataResponseDto<T> extends ResponseDto {
 

	private final T data;
	
	private DataResponseDto(T data) {
		super(true, HttpCodes.OK.getCode(),HttpCodes.OK.getMessage(),LocalDateTime.now());
		this.data = data;
	}
	
	
	private DataResponseDto(T data,String message) {
		super(true,HttpCodes.OK.getCode(),message,LocalDateTime.now());
		this.data= data;
	}
	
	
	public static <T>DataResponseDto<T> of(T data){
		return new DataResponseDto<>(data);
	}
	
	public static <T>DataResponseDto<T> of(T data,String message){
		return new DataResponseDto<>(data,message);
	}


	public static DataResponseDto<Object> empty() {
        return new DataResponseDto<>(null);

	}
	
	
	
	
}
