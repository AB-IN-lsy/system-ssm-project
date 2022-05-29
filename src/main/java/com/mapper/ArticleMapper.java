package com.mapper;

import java.util.List;

import com.entity.Article;

public interface ArticleMapper {
	/**
	 * 查询最近发表的前n条文章
	 * 
	 * @param n 查询出多少条
	 * @return 文章列表
	 */
	List<Article> listRecentArticle(Integer n);

	/**
	 * 查询文章id
	 * 
	 * @param commetArticleId
	 * @return
	 */
	Article getArticleById(int commetArticleId);

}