package com.entity;

import java.util.Date;

import lombok.Data;

@Data
public class User {
	private Integer userId; // ����id
	private String userName; // �˺�
	private String userPass; // ����
	private String userNickname; // �ǳ�
	private String userEmail; // ����
	private String userUrl; // �û�����ַ
	private String userAvatar; // ָ��һ��ͼƬ��ַ(����ֶ�Ŀǰֻ�Ǳ����ֶ�,��ʱ����)
	private String userLastLoginIp; // ����¼��IP
	private Date userRegisterTime; // �û�ע���ʱ��
	private Date userLastLoginTime; // ����¼��ʱ��
	private Integer userStatus; // �û���״̬
	private byte[] userPhoto; // �û���Ƭ,����Ǿ������Ƭ����
}
