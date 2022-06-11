package com.service;

import com.entity.User;
import com.github.pagehelper.PageInfo;

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

	PageInfo<User> getPageUserList(Integer pageIndex, Integer pageSize);

	User getUserById(Integer userId);
}