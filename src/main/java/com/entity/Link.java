package com.entity;

import java.util.Date;

import lombok.Data;

@Data
public class Link {
	private Integer linkId;

	// ���ӵ�ַ
	private String linkUrl;

	// ��������
	private String linkName;

	// ����ͼƬ
	private String linkImage;

	// ��������
	private String linkDescription;

	// �����������ǳ�
	private String linkOwnerNickname;

	// ��ϵ��ʽ
	private String linkOwnerContact;

	// ������ʱ��
	private Date linkUpdateTime;

	// ����ʱ��
	private Date linkCreateTime;

	// ����˳��
	private Integer linkOrder;

	// ����״̬
	private Integer linkStatus;
}
