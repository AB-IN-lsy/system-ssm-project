package com.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entity.Article;
import com.entity.Category;
import com.entity.Tag;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mapper.ArticleMapper;
import com.service.ArticleService;

@Service
public class ArticleSericeImpl implements ArticleService {
	@Autowired
	private ArticleMapper articleMapper;

	public List<Article> listRecentArticle(Integer n) {
		return articleMapper.listRecentArticle(n);
	}

	public PageInfo<Article> getPageArticleList(Integer pageIndex, Integer pageSize) {

		PageHelper.startPage(pageIndex, pageSize); // 底层动态代理，对底层查询的结果进行代理，把查询指令进行了修改，将原本指令加上了limit语句
		// 属于真分页，就是要多少条，查多少条

		List<Article> articleList = articleMapper.findAll();

		// 对每个文章,都要把它对应的大分类,小分类信息查出来
		for (Article a : articleList) {
			// 每个文章要添加分类信息, 欠账
			List<Category> categoryList = articleMapper.listCategoryByArticleId(a.getArticleId());
			a.setCategoryList(categoryList);
		}
		return new PageInfo<Article>(articleList);
	}

	public void add(Article article) {
		// 往文章表中添数据
		articleMapper.addArticle(article);

		// 往文章分类对应表中添数据，（文章id, 目录id）
		List<Category> categorylist = article.getCategoryList();
		for (Category c : categorylist) {
			articleMapper.addArticleCategory(article.getArticleId(), c.getCategoryId());
		}

		// 往文章标签表中添数据
		List<Tag> tagList = article.getTagList();
		for (Tag t : tagList) {
			articleMapper.addArticleTag(article.getArticleId(), t.getTagId());
		}
	}
}