package com.backend.com.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.backend.com.dto.request.BoardPageDto;
import com.backend.com.entity.Article;
import com.backend.com.entity.Board;
import com.backend.com.entity.QBoard;
import com.backend.com.repository.ArticleRepository;
import com.backend.com.repository.BoardRepository;
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
     public void saveArticle(Article article) {
		try {
			log.info("article",article.getTitle());
 			Long boardId = 13L;
			Board board = boardRepository.findById(boardId).orElseGet(() -> createDefaultBoard());
 
		
			
			
			
 			Article paramArticle = Article.builder()
										.title(article.getTitle())
										.content(article.getContent())
										.regId("등록자")
										.createdAt(LocalDateTime.now())
										.updatedAt(LocalDateTime.now())
										.board(board)
										.updId("수정자")
										.build();
			
			if(board.getArticleList() == null) {
				List<Article> articles =new ArrayList<Article>();
				articles.add(paramArticle);
				board.setArticleList(articles);
			}else {
				board.getArticleList().add(paramArticle);
			} 
			
			boardRepository.save(board);
			
		}catch(Exception ex) {
	        log.error("Error saving article", ex);
			throw ex;
		}
		
	}
    private Board createDefaultBoard() {
        // 기본 게시판 생성
        Board defaultBoard = new Board();
//        defaultBoard.setId(13L);
        defaultBoard.setBoardNm("Default Board123123123131231231	23");
        defaultBoard.setRegId("admin");
        defaultBoard.setUpdId("admin");

        // 새 게시판 저장
//        return boardRepository.save(defaultBoard);
        return defaultBoard;
    }
	public Board selectList(BoardPageDto boardPageDto) {
		
		Board board = boardRepository.findById(boardPageDto.getBoardId()).get();
		List<Article> articles =board.getArticleList();
		articles.stream().filter(article -> article.getId() > 0 && article.getId() < 10)
						 .collect(Collectors.toList());
		board.setArticleList(articles);
		
		
		return board;
	}

}
