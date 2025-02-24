package com.backend.com.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import com.backend.com.dto.request.BoardPageDto;
import com.backend.com.dto.response.ArticleDto;
import com.backend.com.entity.Article;
import com.backend.com.entity.Board;
import com.backend.com.entity.QArticle;
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
    private final JPAQueryFactory queryFactory;
	
	
    @Transactional(rollbackFor = Exception.class)  // 체크 예외도 롤백
     public Long saveArticle(ArticleDto articleDto) {
		try {
			log.info("article",articleDto.getTitle());
		       // DTO에서 Article 엔티티 생성
			String userId = SecurityUtil.getCurrentUsername();
			
			
			Board board = boardRepository.findById(articleDto.getBoardId()).get();
	        Article article = Article.builder()
	        						 .id(articleDto.getId())
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
    @Transactional
	public List<ArticleDto> selectArticles( Long boardId) {
		Board board = boardRepository.findById(boardId).get();
		List<ArticleDto> articles=board.getArticleList().stream()
		.map(article -> article.toDto()).collect(Collectors.toList());
		return articles;
	}

	public ArticleDto selectArticle(Long boardId,Long articleId) {
		QArticle article =QArticle.article;
		Article articleOne = queryFactory.selectFrom(article)
					.where(article.board.id.eq(boardId)
					.and(article.id.eq(articleId)))
					.fetchOne();
		ArticleDto result =articleOne.toDto();
		return result;
	}

	public Long deleteArticle(Long boardId, Long articleId) {
		return articleRepository.deleteByBoardIdAndId(boardId,articleId);
	} 

}
