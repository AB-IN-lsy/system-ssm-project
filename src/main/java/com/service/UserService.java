package com.service;
import com.entity.User;

/**
 * 业务层接口
 */
public interface UserService {
	/**
	 * 根据用户名或邮箱登录
	 * @param s 账号或邮箱
	 * @return 用户信息
	 */
	User loginByNameOrEmail(String s);
}