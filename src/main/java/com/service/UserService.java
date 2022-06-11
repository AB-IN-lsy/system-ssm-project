package com.service;

import com.entity.User;
import com.github.pagehelper.PageInfo;

/**
 * 业务层接口
 */
public interface UserService {
	/**
	 * 根据用户名或邮箱登录
	 * 
	 * @param s 账号或邮箱
	 * @return 用户信息
	 */
	User loginByNameOrEmail(String s);

	/**
	 * 添加用户
	 * 
	 * @param user
	 */

	void addUser(User user);

	PageInfo<User> getPageUserList(Integer pageIndex, Integer pageSize);

	User getUserById(Integer userId);
}