package com.backend.com.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.backend.com.dto.RoleDto;
import com.backend.com.dto.response.MemberPermDto;
import com.backend.com.entity.Member;
import com.backend.com.entity.Role;
import com.backend.com.entity.RoleMenuPermission;
import com.backend.com.exception.GeneralException;
import com.backend.com.repository.MemberRepository;
import com.backend.com.utils.SecurityUtil;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class CommonService {
	
	
	private final MemberRepository memberRepository;

	@Transactional
	public MemberPermDto selectMemberPermission(String uri) {
	 
 		String memberId = SecurityUtil.getCurrentUsername();
		Optional.ofNullable(memberId).orElseThrow(()->new GeneralException("로그인된 유저가 없어요 "));
		Member member = memberRepository.findByMemberId(memberId);
		
		List<Role> roles = member.getMemberRoleList().stream().map(memberRole -> memberRole.getRole()).collect(Collectors.toList());
		if(roles == null || roles.isEmpty()) throw new GeneralException("권한없음");
		
		List<RoleDto> roleDts =roles.stream().map(role -> new RoleDto(role.getRoleName(),role.getRolePriorty())).collect(Collectors.toList());
		
		
		List<RoleMenuPermission> roleMenuPerms =member.getMemberRoleList().stream().flatMap(
				memberRole -> memberRole.getRole().getRoleMenuPerms().stream()
		).collect(Collectors.toList());
		
		
		
		
		
		List<Map<String, Object>> myMenuPerRole  =roleMenuPerms.stream().filter(
				roleMenuPerm -> roleMenuPerm.getMenuPermission().getMenu().getMenuUrl().equals(uri))
				.map(roleMenuPerm -> {
					log.info( roleMenuPerm.getMenuPermission().getMenu().getMenuUrl());
					Map<String,Object> map = new HashMap<String,Object>();
					map.put("roleName", roleMenuPerm.getRole().getRoleName());
					map.put("rolePriorty", roleMenuPerm.getRole().getRolePriorty());
					map.put("memberPermVal", roleMenuPerm.getMenuPermission().getMenuPermVal());
					map.put("menuUrl", roleMenuPerm.getMenuPermission().getMenu().getMenuUrl());
					List<String> menuApis = roleMenuPerm.getRole().getRoleMenuApis().stream()
							     .map(roleMenuApi -> roleMenuApi.getMenuApi().getApi().getApiUrl()).collect(Collectors.toList());
					map.put("menuApis",menuApis);
					return map;
				}).collect(Collectors.toList());
		return  MemberPermDto.builder().memberId(memberId).roles(roleDts).myMenuPerRoles(myMenuPerRole).build();
	}
}
