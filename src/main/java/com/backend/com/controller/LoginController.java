package com.backend.com.controller;

import java.util.HashMap;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.com.dto.request.LoginDto;
import com.backend.com.enums.EnumStorage;
import com.backend.com.response.common.DataResponseDto;
import com.backend.com.service.LoginService;
import com.backend.com.service.TokenService;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

 
@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v2/login")
public class LoginController {

	private final LoginService loginService;
	private final TokenService tokenService;

	@PostMapping(value = "/loginAction")
	public DataResponseDto<Object> loginAction(@RequestBody LoginDto login,HttpServletResponse response) {
		HashMap<String,Object> tokenMap = loginService.loginAction(login);
		if(tokenMap!=null) {
			tokenService.setttingTokens(response,login,tokenMap);
			return DataResponseDto.of(tokenMap);
			
		}else {
			return DataResponseDto.of(tokenMap);
		}
	}  
	  
} 
 