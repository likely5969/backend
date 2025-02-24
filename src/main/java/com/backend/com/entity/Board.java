package com.backend.com.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_board")
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Getter
@Setter
public class Board {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "BOARD_ID",  columnDefinition = "BIGINT COMMENT '게시판ID'")
	private Long id;

	@Column(name = "BOARD_NM", nullable = false,length = 100, columnDefinition = "VARCHAR(100) COMMENT '게시판명'")
	private String boardNm;
	
	@OneToMany(mappedBy = "board", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Article> articleList = new ArrayList<Article>();
	
	@Builder
	private Board(Long boardId, String boardNm) {
		this.boardNm= boardNm;
		this.id = boardId;
	}
	
	public Board createBoard(Long boardId, String boardNm) {
		return Board.builder().boardId(boardId).boardNm(boardNm).build();
	}
	
	public void addArticle(Article article) {
		this.articleList.add(article);
	}
	
	
	@Column(name="REG_ID" ,nullable=false,columnDefinition="VARCHAR(100) comment '등록자'")
	private String regId;
	
	
	@Column(name = "CREATED_AT", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private LocalDateTime createdAt;
	
	@Column(name="UPD_ID" ,nullable=false,columnDefinition="VARCHAR(100) comment '등록자'")
	private String updId;
	
	@Column(name = "UPDATED_AT", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private LocalDateTime updatedAt;
 
}
