package com.entity;

import lombok.Data;

@Data
public class Menu {

	private Integer menuId;
	private String menuName;
	private String menuUrl;

	// ���ֵ�����������Ƕ����˵�(1)������Ҫ�˵���(2)
	private Integer menuLevel;

	private String menuIcon;

	// �˵���˳��
	private Integer menuOrder;
}