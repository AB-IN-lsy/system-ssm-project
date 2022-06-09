package com.entity;

import java.util.Date;

import lombok.Data;

@Data
public class User {
	private Integer userId; // 自增id
	private String userName; // 账号
	private String userPass; // 密码
	private String userNickname; // 昵称
	private String userEmail; // 邮箱
	private String userUrl; // 用户的网址
	private String userAvatar; // 指向一个图片地址(这个字段目前只是保留字段,暂时不用)
	private String userLastLoginIp; // 最后登录的IP
	private Date userRegisterTime; // 用户注册的时间
	private Date userLastLoginTime; // 最后登录的时间
	private Integer userStatus; // 用户的状态
	private byte[] userPhoto; // 用户照片,存的是具体的照片数据
}
