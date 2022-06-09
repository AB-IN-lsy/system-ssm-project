package com.entity;

import java.util.Date;

import lombok.Data;

@Data
public class Comment {
	// ����id
	private Integer commentId;

	// ���۵ĸ���ID( �������˵�ID? )
	private Integer commentPid;

	// �����˵�����
	private String commentPname;

	// �����۵����µ�ID
	private Integer commentArticleId;

	// ����������
	private String commentAuthorName;

	// ����������
	private String commentAuthorEmail;

	// �����ߵ���ַ
	private String commentAuthorUrl;

	// �����ߵ�ͷ���ַ
	private String commentAuthorAvatar;

	// ���۵�����
	private String commentContent;

	private String commentAgent;

	// �����˵�IP
	private String commentIp;

	// ����������ʱ��
	private Date commentCreateTime;

	// �����˵Ľ�ɫ(����Ա 1, �ÿ� 0)
	private Integer commentRole;

	// �����۵��������ĸ� �����ݿ��ֶ�
	private Article article;

}