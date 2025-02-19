package com.backend.com.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.backend.com.response.common.DataResponseDto;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

 
@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v2/auth")
public class AuthController {

	@GetMapping("/menuAuth")
	public DataResponseDto<Object> auth(@RequestParam("pageUrl") String pageUrl,HttpServletResponse response) {
		log.info(pageUrl);
	
	
		return null;
	}
} 
 