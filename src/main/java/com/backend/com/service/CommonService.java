package com.backend.com.service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.backend.com.dto.RoleDto;
import com.backend.com.dto.response.MemberPermDto;
import com.backend.com.entity.Member;
import com.backend.com.entity.MemberRole;
import com.backend.com.entity.MenuPermission;
import com.backend.com.exception.GeneralException;
import com.backend.com.repository.MemberRepository;
import com.backend.com.security.CustomUserDetails;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class CommonService {
	
	
	private final MemberRepository memberRepository;

	@Transactional
	public MemberPermDto selectMemberPermission(String uri) {
		// interceptor에서 접속한 사람이 yellowplace 이 사람이 권한이 뭐고 , 지금 현재메뉴 /main 페이지에 대한 권한이 얼만지 (1~15) 동시에  
	    // /main 에 속한 api들중에서 yellowplace 권한에서만 사용할수있는 api들을 가지고 와서 
		// 한개의 dto에 담아서 리턴 한다 
		
		
		 
		return null;
	}
}
