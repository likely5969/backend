package com.backend.com.entity;

import jakarta.persistence.Access;
import jakarta.persistence.AccessType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.NoArgsConstructor;

@Entity
@Table(name ="tb_perm")
@Access(AccessType.FIELD) // 필드 접근 방식을 명시적으로 설정
@NoArgsConstructor
public class Permission {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	@Column(name = "PERM_ID", unique = true, nullable = false ,columnDefinition = "BIGINT COMMENT '사용자권한ID'")
	private Long permId;
	
	@Column(name = "PERM_NM", nullable = false ,length=80,columnDefinition = "VARCHAR(80) COMMENT '사용자권한명'" )
	private String permNm;
	
	@Column(name = "PERM_VAL", nullable = false ,columnDefinition = "BIGINT COMMENT '사용자권한총수치'")
	private Long permVal;

}
