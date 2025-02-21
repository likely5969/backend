package com.backend.com.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.com.dto.request.BoardPageDto;
import com.backend.com.dto.response.ArticleDto;
import com.backend.com.entity.Article;
import com.backend.com.entity.Board;
import com.backend.com.entity.Member;
import com.backend.com.response.common.DataResponseDto;
import com.backend.com.service.BoardService;
import com.backend.com.service.CommonService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

 
@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v2/board")
public class BoardController {

	private final BoardService boardService;
	
	@PostMapping(value = "/writeProc")
	public DataResponseDto<Object> writeProc(@RequestBody Article article,HttpServletResponse response) {
 		boardService.saveArticle(article);
		return DataResponseDto.of("");
 	}
	
	@GetMapping(value="/list/{boardId}/{pageNo}")
	public DataResponseDto<Object> list(@PathVariable("pageNo") Long pageNo,@PathVariable("boardId") Long boardId,HttpServletResponse response) {
		BoardPageDto param =new BoardPageDto();
		param.setBoardId(boardId);
		param.setPageNo(pageNo);
		 List<ArticleDto> articles = boardService.selectList(param);
		return DataResponseDto.of(articles);

	}
	  
} 
 