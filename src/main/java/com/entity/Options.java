package com.entity;

import lombok.Data;

@Data
public class Options {
	private Integer optionId;

	// վ������
	private String optionSiteTitle;

	// վ������
	private String optionSiteDescrption;

	// ��ҳ����
	private String optionMetaDescrption;

	// ��ҳ�ؼ���
	private String optionMetaKeyword;

	// ͷ��ͼƬ
	private byte[] optionAboutsitePhoto;

	// �ǳ�
	private String optionAboutsiteTitle;

	// ˵��
	private String optionAboutsiteContent;

	// ΢�Ŷ�ά��ͼƬ
	private byte[] optionAboutsiteWechatphoto;

	// QQ
	private String optionAboutsiteQq;

	// git��ַ
	private String optionAboutsiteGithub;

	// ΢��
	private String optionAboutsiteWeibo;

	private String optionTongji;

	private Integer optionStatus;
}
