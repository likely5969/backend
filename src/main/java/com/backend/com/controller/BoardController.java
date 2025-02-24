package com.backend.com.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.com.dto.response.ArticleDto;
import com.backend.com.response.common.DataResponseDto;
import com.backend.com.service.BoardService;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

 
@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v2/board")
public class BoardController {

	private final BoardService boardService;
	
	
	
	@GetMapping(value="/list/{boardId}/{pageNo}")
	public DataResponseDto<Object> articles(@PathVariable("boardId") Long boardId , @PathVariable("pageNo") Long pageNo ) {
		List<ArticleDto> articles = boardService.selectArticles(boardId);
		return DataResponseDto.of(articles);

	}
	@GetMapping(value="/view/{boardId}/{articleId}")
	public DataResponseDto<Object> article(@PathVariable("boardId") Long boardId , @PathVariable("articleId") Long articleId,HttpServletResponse response) {
		ArticleDto article = boardService.selectArticle(boardId,articleId);
		return DataResponseDto.of(article);

	}
	@PostMapping(value = "/writeAction")
	public DataResponseDto<Object> writeProc(@RequestBody ArticleDto articleDto,HttpServletResponse response) {
 		Long result = boardService.saveArticle(articleDto);
 		return DataResponseDto.of(result);
 	}
	@PostMapping(value="/delete/{boardId}/{articleId}")
	public DataResponseDto<Object> deleteArticle(@PathVariable ("boardId")Long boardId, @PathVariable("articleId") Long articleId){
		log.info("boardId:::::::"+boardId);
		log.info("articleId:::::::"+articleId);
		Long result = boardService.deleteArticle(boardId,articleId);
 		return DataResponseDto.of(result);

	}
	
} 
 