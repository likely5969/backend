package com.backend.com.entity;

import java.time.LocalDateTime;

import com.backend.com.dto.response.ArticleDto;
import com.backend.com.entity.common.CommonEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_article")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor 
 public class Article{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ARTICLE_ID", columnDefinition = "BIGINT COMMENT '게시글ID'")
	private Long id;

	@Column(name = "ARTICLE_TITLE", columnDefinition = "VARCHAR(100) COMMENT '게시글제목'")
	private String title;

	@Column(name = "ARTICLE_CONTENT", columnDefinition = "VARCHAR(600) COMMENT '게시글내용'")
	private String content;

	@Column(name = "REG_ID", nullable = false, columnDefinition = "VARCHAR(100) comment '등록자'")
	private String regId;

	@Column(name = "CREATED_AT", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private LocalDateTime createdAt = LocalDateTime.now();

	@Column(name = "UPD_ID", nullable = false, columnDefinition = "VARCHAR(100) comment '등록자'")
	private String updId;

	@Column(name = "UPDATED_AT", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private LocalDateTime updatedAt = LocalDateTime.now();

	
	@ManyToOne
	@JoinColumn(name = "BOARD_ID", nullable = false)
	private Board board;

	
	// DTO 변환 메서드
    public ArticleDto toDto() {
        return new ArticleDto(this);
    }
}
