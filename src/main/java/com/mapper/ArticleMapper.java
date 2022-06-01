package com.mapper;

import java.util.List;

import com.entity.Article;
import com.entity.Category;

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

	/**
	 * ��ѯ���е�����
	 * 
	 * @return ���б�
	 */
	List<Article> findAll();

	/**
	 * ��������id,��ѯ���������ķ���(�����,С����)
	 * 
	 * @param articleId ����id
	 * @return �����б�
	 */
	List<Category> listCategoryByArticleId(Integer articleId);

}