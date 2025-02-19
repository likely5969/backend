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
@Table(name = "TB_ROLE_MENU_API")
@Getter
public class RoleMenuApi {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ROLE_MENU_API_ID")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="ROLE_ID")
	private Role role;
	
	
	@ManyToOne
	@JoinColumn(name="MENU_API_ID")
	private MenuApi menuApi;
	
}
