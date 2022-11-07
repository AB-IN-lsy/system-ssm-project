package com.mapper;

import java.util.List;

import com.entity.User;

public interface UserMapper {
	/**
	 * �����û�������������¼
	 * 
	 * @param s �û���������
	 * @return �û���Ϣ
	 */
	User loginByNameOrEmail(String s);

	/**
	 * ����û�
	 * 
	 * @param user
	 */
	void addUser(User user);

	User getUserById(Integer userId);

	List<User> listUser();
}
