package com.service;

import java.util.List;

import com.entity.Article;
import com.github.pagehelper.PageInfo;

public interface ArticleService {
	/**
	 * 查询最近发表的前n条文章
	 * 
	 * @param n 查询出多少条
	 * @return 文章列表
	 */
	List<Article> listRecentArticle(Integer n);

	PageInfo<Article> getPageArticleList(Integer pageIndex, Integer pageSize);
}