package com.mapper;

import java.util.List;

import com.entity.Comment;

public interface CommentMapper {
	/**
	 * 查询最近发表的前n条评论
	 * 
	 * @param n
	 * @return
	 */
	List<Comment> listRecentComment(int n);
}