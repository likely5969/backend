package com.backend.com.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "TB_MEMBER_ROLE")

public class MemberRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MEMBER_ROLE_ID")
    private Long id;
	
	@ManyToOne
	@JoinColumn(name="MEMBER_ID",nullable=false )
	private Member member;
	
	@ManyToOne
	@JoinColumn(name="ROLE_ID",nullable=false )
	private Role role;

	public Role getRole() {
		return role;
	}
	
}
