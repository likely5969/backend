package com.backend.com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import com.backend.com.entity.Article;

public interface ArticleRepository extends JpaRepository<Article, Long>, QuerydslPredicateExecutor<Article> {

	Long deleteByBoardIdAndId(Long boardId, Long articleId);

}
