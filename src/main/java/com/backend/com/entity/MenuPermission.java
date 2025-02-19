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

@Entity
@Table(name = "TB_MENUPERM")
@Getter
public class MenuPermission {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "MENU_PERM_ID", columnDefinition = "BIGINT COMMENT '권한별 메뉴'")
	private Long id;

	@Column(name = "MENU_PERM_VALUE", nullable = false, columnDefinition = "BIGINT COMMENT '실제메뉴권한 수치'")
	private Long menuPermVal;

	@OneToMany(mappedBy="menuPermission", cascade = CascadeType.ALL)
	private List<RoleMenuPermission> roleMenuPerms;
	
   @ManyToOne
   @JoinColumn(name="MENU_ID",nullable =false)
   private Menu menu;
   
}
