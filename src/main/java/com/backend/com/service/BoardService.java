package com.backend.com.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.backend.com.dto.request.BoardPageDto;
import com.backend.com.dto.response.ArticleDto;
import com.backend.com.entity.Article;
import com.backend.com.entity.Board;
import com.backend.com.entity.QBoard;
import com.backend.com.repository.ArticleRepository;
import com.backend.com.repository.BoardRepository;
import com.backend.com.utils.SecurityUtil;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class BoardService {
	private final BoardRepository boardRepository;
	private final ArticleRepository articleRepository;

    @Transactional(rollbackFor = Exception.class)  // 체크 예외도 롤백
     public Long saveArticle(ArticleDto articleDto) {
		try {
			log.info("article",articleDto.getTitle());
		       // DTO에서 Article 엔티티 생성
			String userId = SecurityUtil.getCurrentUsername();
			
			
			Board board = boardRepository.findById(articleDto.getBoardId()).get();
	        Article article = Article.builder()
	        						 .title(articleDto.getTitle())
	        						 .content(articleDto.getContent())
	        						 .board(board).regId(userId).updId(userId).build();
			Article rearticle = articleRepository.save(article);
			return rearticle.getId();
		}catch(Exception ex) {
	        log.error("Error saving article", ex);
			throw ex;
		}
		
	}
 
	public List<ArticleDto> selectList(BoardPageDto boardPageDto) {
		Board board = boardRepository.findById(boardPageDto.getBoardId()).get();
		List<ArticleDto> articles=board.getArticleList().stream()
		.map(Article::toDto).collect(Collectors.toList());
		return articles;
	}

}
