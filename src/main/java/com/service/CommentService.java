package com.service;

import java.util.List;

import com.entity.Comment;

public interface CommentService {
	/**
	 * ��ѯ��������ǰn������
	 * 
	 * @param i
	 * @return
	 */
	List<Comment> listRecentComment(Integer n);
}