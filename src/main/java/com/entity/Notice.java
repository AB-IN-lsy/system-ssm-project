package com.entity;

import java.util.Date;

import lombok.Data;

@Data

public class Notice {
	private Integer noticeId;
	private String noticeTitle;
	private String noticeContent;
	private Date noticeCreateTime;
	private Date noticeUpdateTime;
	private Integer noticeStatus;
	private Integer noticeOrder;
}
