package com.backend.com.dto.response;

import java.util.List;
import java.util.Map;

import com.backend.com.dto.RoleDto;
import com.backend.com.entity.Role;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MemberPermDto {
	
	private String memberId ;
	private List<RoleDto> roles;
	private List<Map<String, Object>> myMenuPerRoles;
	
}
