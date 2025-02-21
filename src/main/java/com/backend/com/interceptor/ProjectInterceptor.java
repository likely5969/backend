package com.backend.com.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;
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
		uri =request.getParameter("pageUrl");
		/* react 주소 인지 //api인지 확인 api는 이미 리액트 주소로 권한 가져올떄 api리스트를 페이지에 뿌려서 
		 * react 에서 체크 후에 api를 사용하니깐 아래 로직이 필요없음 
		 */
		
		if(uri==null) {
			return true;
		}
		MemberPermDto memberPermDto = commonService.selectMemberPermission(uri);
		request.setAttribute("MemberPermDto", memberPermDto);
		return true;
	}
}
