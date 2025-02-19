package com.backend.com.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "TB_MENU")
@NoArgsConstructor
public class Menu {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "MENU_ID", columnDefinition = "BIGINT COMMENT '메뉴아이디'")
	private Long id;

	@Column(name = "MENU_NAME", nullable = false, columnDefinition = "VARCHAR(100) COMMENT '메뉴명'")
	private String menuNm;
	
	@Column(name = "MENU_URL", nullable = false, columnDefinition = "VARCHAR(100) COMMENT '메뉴URL'")
	private String menuUrl;

	public String getMenuUrl() {
		return menuUrl;
	}

	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl;
	}
	
	
	@OneToMany(mappedBy="menu", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<MenuApi> apiList;
	
	@OneToMany(mappedBy="menu", cascade = CascadeType.ALL, orphanRemoval = true )
	private List<MenuPermission> menuPerms;
	
	
}