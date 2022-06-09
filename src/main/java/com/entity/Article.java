package com.entity;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class Article {

	private Integer articleId;

	// 创建文章的用户
	private Integer articleUserId;

	// 文章标题
	private String articleTitle;

	// 浏览次数
	private Integer articleViewCount;

	// 回复次数
	private Integer articleCommentCount;

	// 喜欢次数
	private Integer articleLikeCount;

	// 创建时间
	private Date articleCreateTime;

	// 更新时间
	private Date articleUpdateTime;

	// 是否回复
	private Integer articleIsComment;

	// 状态
	private Integer articleStatus;

	// 排序
	private Integer articleOrder;

	// 文章内容
	private String articleContent;

	// 言章概要
	private String articleSummary;

	// 用户
	private User user;

	// 文章属于哪些分类, 在此项目一共有有两级分类，大分类和小分类
	private List<Category> categoryList;

	// 文章挂有哪些标签
	private List<Tag> tagList;
}
