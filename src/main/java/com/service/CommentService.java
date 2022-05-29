package com.service;

import java.util.List;

import com.entity.Comment;

public interface CommentService {
	/**
	 * 查询最近发表的前n条评论
	 * 
	 * @param i
	 * @return
	 */
	List<Comment> listRecentComment(Integer n);
}