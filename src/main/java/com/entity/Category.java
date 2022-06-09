package com.entity;

import lombok.Data;

@Data
public class Category {
	// 分类的自增ID
	private Integer categoryId;

	// 分类的父级id
	private Integer categoryPid;

	// 分类名称
	private String categoryName;

	// 分类描述
	private String categoryDescription;

	// 分类的顺序
	private Integer categoryOrder;

	// 分类的图标
	private String categoryIcon;

	// 文章数量(非数据库字段)
	private Integer articleCount;

}