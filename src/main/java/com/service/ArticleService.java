package com.service;

import java.util.List;

import com.entity.Article;

public interface ArticleService {
	/**
	 * ��ѯ��������ǰn������
	 * 
	 * @param n ��ѯ��������
	 * @return �����б�
	 */
	List<Article> listRecentArticle(Integer n);
}