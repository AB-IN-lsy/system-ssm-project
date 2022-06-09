package com.entity;

import lombok.Data;

@Data
public class Category {
	// ���������ID
	private Integer categoryId;

	// ����ĸ���id
	private Integer categoryPid;

	// ��������
	private String categoryName;

	// ��������
	private String categoryDescription;

	// �����˳��
	private Integer categoryOrder;

	// �����ͼ��
	private String categoryIcon;

	// ��������(�����ݿ��ֶ�)
	private Integer articleCount;

}