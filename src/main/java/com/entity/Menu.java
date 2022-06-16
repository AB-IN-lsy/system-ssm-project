package com.entity;

import lombok.Data;

@Data
public class Menu {

	private Integer menuId;
	private String menuName;
	private String menuUrl;

	// 这个值是用来区分是顶部菜单(1)还是主要菜单的(2)
	private Integer menuLevel;

	private String menuIcon;

	// 菜单的顺序
	private Integer menuOrder;
}