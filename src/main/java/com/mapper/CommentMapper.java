package com.mapper;

import java.util.List;

import com.entity.Comment;

public interface CommentMapper {
	/**
	 * ��ѯ��������ǰn������
	 * 
	 * @param n
	 * @return
	 */
	List<Comment> listRecentComment(int n);
}