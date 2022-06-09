package com.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

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

	/**
	 * 往文章表中添加数据
	 * 
	 * @param article ,可以用来接收返回的自增主键
	 */
	void addArticle(Article article);

	/**
	 * 往 文章分类表 中添加数据
	 * 
	 * @param articleId  文章id
	 * @param categoryId 分类id
	 * 
	 *                   一定要记住，mybatis对于多个参数是处理不了的，得加上注释
	 */
	void addArticleCategory(@Param("articleId") Integer articleId, @Param("categoryId") Integer categoryId);

	/**
	 * 往 文章标签表 中添加数据
	 * 
	 * @param articleId 文章id
	 * @param tagId     标签id
	 */
	void addArticleTag(@Param("articleId") Integer articleId, @Param("tagId") Integer tagId);

}