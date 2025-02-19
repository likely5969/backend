package com.backend.com.entity;

import java.util.List;

import jakarta.annotation.Generated;
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
@Table(name = "tb_api")
public class Api {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "API_ID", columnDefinition = "BIGINT COMMENT 'API 정보아이디'")
	private Long id;

	@Column(name = "API_URL", columnDefinition = "VARCHAR(100) COMMENT 'API주소'")
	private String apiUrl;
 
    
    @OneToMany(mappedBy = "api", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<MenuApi> MenuApis;
    
    
}
