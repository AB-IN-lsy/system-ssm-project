package com.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entity.Article;
import com.entity.Comment;
import com.mapper.ArticleMapper;
import com.mapper.CommentMapper;
import com.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService {
	@Autowired
	private CommentMapper commentMapper;

	@Autowired
	private ArticleMapper artileMapper;

	/**
	 * 查询最近的n条评论信息,每条评论信息中,都含有被评论的文章的信息
	 */
	public List<Comment> listRecentComment(Integer n) {

		List<Comment> commentList = commentMapper.listRecentComment(n);

		for (int i = 0; i < commentList.size(); i++) {

			// 得到被评论的文章的id
			int commetArticleId = commentList.get(i).getCommentArticleId();

			// 根据文章id得到文章信息
			Article article = artileMapper.getArticleById(commetArticleId);

			// 把文章信息传给评论
			commentList.get(i).setArticle(article);

		}

		return commentList;
	}
}