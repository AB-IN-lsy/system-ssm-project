package com.mapper;

import com.entity.User;

public interface UserMapper {
	/**
	 * �����û�������������¼
	 * 
	 * @param s �û���������
	 * @return �û���Ϣ
	 */
	User loginByNameOrEmail(String s);
}
