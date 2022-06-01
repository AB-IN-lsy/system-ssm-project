package com.mapper;

import java.util.List;

import com.entity.Article;
import com.entity.Category;

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

	/**
	 * 查询所有的文章
	 * 
	 * @return 文列表
	 */
	List<Article> findAll();

	/**
	 * 根据文章id,查询文章所属的分类(大分类,小分类)
	 * 
	 * @param articleId 文章id
	 * @return 分类列表
	 */
	List<Category> listCategoryByArticleId(Integer articleId);

}