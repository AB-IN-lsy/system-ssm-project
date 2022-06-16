package com.entity;

import java.util.Date;

import lombok.Data;

@Data
public class Link {
	private Integer linkId;

	// 链接地址
	private String linkUrl;

	// 链接名称
	private String linkName;

	// 链接图片
	private String linkImage;

	// 链接描述
	private String linkDescription;

	// 链接所有者昵称
	private String linkOwnerNickname;

	// 联系方式
	private String linkOwnerContact;

	// 最后更新时间
	private Date linkUpdateTime;

	// 创建时间
	private Date linkCreateTime;

	// 链接顺序
	private Integer linkOrder;

	// 链接状态
	private Integer linkStatus;
}
