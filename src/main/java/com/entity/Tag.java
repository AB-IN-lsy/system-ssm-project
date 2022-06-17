package com.entity;

import lombok.Data;

@Data
public class Tag {
	public Tag() {
	}

	public Tag(Integer tagId) {
		this.tagId = tagId;
	}

	private Integer tagId;
	private String tagName;
	private String tagDescription;
	private Integer articleCount; // ·ÇÊý¾Ý¿â×Ö¶Î
}