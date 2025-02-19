package com.backend.com.service;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.backend.com.dto.RoleDto;
import com.backend.com.entity.Member;
import com.backend.com.repository.MemberRepository;
import com.backend.com.security.CustomUserDetails;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CustomUserDetailsService implements UserDetailsService {
	private final MemberRepository memberRepository;
 	
	
	@Override
	public CustomUserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
 		Member member  = memberRepository.findByMemberId(userId);
		Set<RoleDto> roleDtos = member.getRoleList().stream().map(role -> new RoleDto(role.getRoleName(),role.getRolePriorty())).collect(Collectors.toSet());
		member.setRoleDtos(roleDtos);
  		return new CustomUserDetails(member);
	}

}