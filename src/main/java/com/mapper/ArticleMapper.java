package com.mapper;

import java.util.List;

import com.entity.Article;

public interface ArticleMapper {
	/**
	 * ��ѯ��������ǰn������
	 * 
	 * @param n ��ѯ��������
	 * @return �����б�
	 */
	List<Article> listRecentArticle(Integer n);

	/**
	 * ��ѯ����id
	 * 
	 * @param commetArticleId
	 * @return
	 */
	Article getArticleById(int commetArticleId);

}