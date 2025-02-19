package com.backend.com.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "TB_MENU_API")
@NoArgsConstructor
@Getter
public class MenuApi {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "MENU_API_ID", columnDefinition = "BIGINT COMMENT '메뉴 API아이디'")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="MENU_ID")
	private Menu menu;
	
	@ManyToOne
	@JoinColumn(name="API_ID")
	private Api api;
	
	
	@OneToMany(mappedBy="menuApi",cascade = CascadeType.ALL, orphanRemoval = true)
	private List<RoleMenuApi> roleMenuApis;
	
}