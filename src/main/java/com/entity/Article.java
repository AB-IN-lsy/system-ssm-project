package com.entity;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class Article {

	private Integer articleId;

	// �������µ��û�
	private Integer articleUserId;

	// ���±���
	private String articleTitle;

	// �������
	private Integer articleViewCount;

	// �ظ�����
	private Integer articleCommentCount;

	// ϲ������
	private Integer articleLikeCount;

	// ����ʱ��
	private Date articleCreateTime;

	// ����ʱ��
	private Date articleUpdateTime;

	// �Ƿ�ظ�
	private Integer articleIsComment;

	// ״̬
	private Integer articleStatus;

	// ����
	private Integer articleOrder;

	// ��������
	private String articleContent;

	// ���¸�Ҫ
	private String articleSummary;

	// �û�
	private User user;

	// ����������Щ����, �ڴ���Ŀһ�������������࣬������С����
	private List<Category> categoryList;

	// ���¹�����Щ��ǩ
	private List<Tag> tagList;
}
