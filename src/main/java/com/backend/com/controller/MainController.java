package com.backend.com.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.com.dto.response.MemberPermDto;
import com.backend.com.response.common.DataResponseDto;
import com.backend.com.service.CommonService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v2/auth")
public class MainController {
	@GetMapping(value = "/main")
	public DataResponseDto<Object> mainPage(HttpServletRequest request, HttpServletResponse response) {
		MemberPermDto memberPermDto = (MemberPermDto) request.getAttribute("MemberPermDto");
		return DataResponseDto.of(memberPermDto);
	}

}
