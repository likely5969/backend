package com.backend.com.dto.response;

import java.time.LocalDateTime;

import com.backend.com.entity.Article;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class ArticleDto {
	private Long id;
	private String title;
	private String content;
	private String regId;
	private LocalDateTime createdAt;
	private String updId;
	private LocalDateTime updatedAt;
	private Long boardId;
	// 생성자에서 Article 객체를 받아서 DTO로 변환
	public ArticleDto(Article article) {
		this.id = article.getId();
		this.title = article.getTitle();
		this.content = article.getContent();
		this.regId = article.getRegId();
		this.createdAt = article.getCreatedAt();
		this.updId = article.getUpdId();
		this.updatedAt = article.getUpdatedAt();
		this.boardId = article.getBoard().getId();
	}
}
