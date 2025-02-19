package com.backend.com.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;

@Entity
@Table(name = "TB_ROLE_MENUPERM")
@Getter
public class RoleMenuPermission {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ROLE_MENUPERM_ID", nullable = false)
	private Long id;
	
	
	@ManyToOne
	@JoinColumn(name="ROLE_ID" ,nullable =false)
	private Role role;
	  

	@ManyToOne
	@JoinColumn(name="MENU_PERM_ID",nullable =false)
	private MenuPermission menuPermission;

}
