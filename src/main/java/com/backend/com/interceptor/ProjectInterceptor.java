package com.backend.com.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.backend.com.dto.response.MemberPermDto;
import com.backend.com.service.CommonService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
@Component
@Slf4j
@RequiredArgsConstructor
public class ProjectInterceptor implements HandlerInterceptor{
	
	private final CommonService commonService;
 	
	@Override
	public boolean preHandle(HttpServletRequest request , HttpServletResponse resposne,Object object) {
		String uri =request.getRequestURI();
 		MemberPermDto memberPermDto = commonService.selectMemberPermission(uri);
		request.setAttribute("MemberPermDto", memberPermDto);
		return true;
	}
}
