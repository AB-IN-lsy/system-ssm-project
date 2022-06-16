package com.entity;

import lombok.Data;

@Data
public class Options {
	private Integer optionId;

	// 站点名称
	private String optionSiteTitle;

	// 站点描述
	private String optionSiteDescrption;

	// 首页描述
	private String optionMetaDescrption;

	// 首页关键词
	private String optionMetaKeyword;

	// 头象图片
	private byte[] optionAboutsitePhoto;

	// 昵称
	private String optionAboutsiteTitle;

	// 说明
	private String optionAboutsiteContent;

	// 微信二维码图片
	private byte[] optionAboutsiteWechatphoto;

	// QQ
	private String optionAboutsiteQq;

	// git地址
	private String optionAboutsiteGithub;

	// 微博
	private String optionAboutsiteWeibo;

	private String optionTongji;

	private Integer optionStatus;
}
