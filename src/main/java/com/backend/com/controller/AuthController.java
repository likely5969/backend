package com.backend.com.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.backend.com.dto.response.MemberPermDto;
import com.backend.com.response.common.DataResponseDto;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

 
@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v2/auth")
public class AuthController {

	@GetMapping("/checkPerm")
	public DataResponseDto<Object> auth(@RequestParam("pageUrl") String pageUrl,HttpServletRequest request,HttpServletResponse response) {
		
	      // 현재 인증된 사용자 정보를 가져옵니다.
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        
        // 인증된 사용자 여부 확인
        if (authentication != null && authentication.isAuthenticated()) {
        	log.info("인증된 사람 ");
        }else {
        	log.info("인증안된 사람 ");
        }
		
		
		log.info(pageUrl);
		MemberPermDto memberPermDto = (MemberPermDto) request.getAttribute("MemberPermDto");
		return DataResponseDto.of(memberPermDto);
	}
} 
 