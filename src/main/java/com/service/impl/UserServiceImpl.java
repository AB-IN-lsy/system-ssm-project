package com.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entity.User;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mapper.UserMapper;
import com.service.UserService;

@Service // 千万不要忘了这个注解
public class UserServiceImpl implements UserService {

	@Autowired // 还有这个注解,它的作用是让spring帮我们注入依赖对象
	private UserMapper userMapper;

	public User loginByNameOrEmail(String s) {
		return userMapper.loginByNameOrEmail(s);
	}

	@Override
	public void addUser(User user) {
		userMapper.addUser(user);

	}

	@Override
	public PageInfo<User> getPageUserList(Integer pageIndex, Integer pageSize) {
		PageHelper.startPage(pageIndex, pageSize);

		List<User> userList = userMapper.findAll();

		return new PageInfo<User>(userList);
	}

	@Override
	public User getUserById(Integer userId) {
		return userMapper.getUserById(userId);
	}
}