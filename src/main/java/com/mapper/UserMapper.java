package com.mapper;

import com.entity.User;

public interface UserMapper {
	/**
	 * 根据用户名或邮箱名登录
	 * 
	 * @param s 用户名或邮箱
	 * @return 用户信息
	 */
	User loginByNameOrEmail(String s);
}
