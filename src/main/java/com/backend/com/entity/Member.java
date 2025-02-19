package com.backend.com.entity;

import java.util.Set;
import java.util.stream.Collectors;

import com.backend.com.dto.RoleDto;

import jakarta.persistence.Access;
import jakarta.persistence.AccessType;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_member")
@Access(AccessType.FIELD) // 필드 접근 방식을 명시적으로 설정
@NoArgsConstructor
public class Member {
	@Id
	@Column(name = "MEMBER_ID", unique = true, nullable = false, length = 100, columnDefinition = "VARCHAR(100) COMMENT '사용자계정명'")
	private String memberId;

	@Column(name = "PASSWORD", nullable = false, length = 100, columnDefinition = "VARCHAR(100) COMMENT '사용자비번'")
	private String password;

	@OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<MemberRole> memberRoleList;

	
	@Transient
	private Set<RoleDto> roleDtos;
	
	
	
	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<MemberRole> getMemberRoleList() {
		return memberRoleList;
	}

	public void setMemberRoleList(Set<MemberRole> memberRoleList) {
		this.memberRoleList = memberRoleList;
	}
	
	public Set<Role> getRoleList(){
		return memberRoleList.stream().map(memberRole -> memberRole.getRole()).collect(Collectors.toSet());
	}

	public Set<RoleDto> getRoleDtos() {
		return roleDtos;
	}

	public void setRoleDtos(Set<RoleDto> roleDtos) {
		this.roleDtos = roleDtos;
	}
	
	   

}
