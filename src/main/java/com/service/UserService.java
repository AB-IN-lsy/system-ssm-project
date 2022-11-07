package com.service;

import java.util.List;

import com.entity.User;

/**
 * ҵ���ӿ�
 */
public interface UserService {
	/**
	 * �����û����������¼
	 * 
	 * @param s �˺Ż�����
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