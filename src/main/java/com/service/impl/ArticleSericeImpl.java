package com.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entity.Article;
import com.mapper.ArticleMapper;
import com.service.ArticleService;

@Service
public class ArticleSericeImpl implements ArticleService {
	@Autowired
	private ArticleMapper articleMapper;

	public List<Article> listRecentArticle(Integer n) {
		return articleMapper.listRecentArticle(n);
	}
}