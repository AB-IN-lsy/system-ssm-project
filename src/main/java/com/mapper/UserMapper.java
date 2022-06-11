package com.mapper;

import java.util.List;

import com.entity.User;

public interface UserMapper {
	/**
	 * 根据用户名或邮箱名登录
	 * 
	 * @param s 用户名或邮箱
	 * @return 用户信息
	 */
	User loginByNameOrEmail(String s);

	/**
	 * 添加用户
	 * 
	 * @param user
	 */
	void addUser(User user);

	List<User> findAll();

	User getUserById(Integer userId);
}
