package com.backend.com.entity;

import java.util.List;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;

@Entity
@Getter
@Table(name = "tb_role")
public class Role {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ROLE_ID",  columnDefinition = "BIGINT COMMENT '권한아이디'")
	private Long id;

	@Column(name = "ROLE_NM", nullable = false, unique = true, length = 100, columnDefinition = "VARCHAR(100) COMMENT '권한명'")
	private String roleName;
	
	@Column(name = "ROLE_DESCRIPTION", nullable = false,   columnDefinition = "VARCHAR(100) COMMENT '권한설명'")
	private String roleDiscription;
	
	
	@Column(name="ROLE_PRIORTY", nullable = false,   columnDefinition = "BIGINT COMMENT '권한우선순위'")
	private Long rolePriorty;
	

	@OneToMany(mappedBy = "role", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<MemberRole> memberRoles;

	@OneToMany(mappedBy = "role", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<RoleMenuPermission> roleMenuPerms;
	
	@OneToMany(mappedBy="role",cascade = CascadeType.ALL, orphanRemoval = true)
	private List<RoleMenuApi> roleMenuApis;
	
}
