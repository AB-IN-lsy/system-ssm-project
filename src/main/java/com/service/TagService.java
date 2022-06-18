package com.service;

import java.util.List;

import com.entity.Tag;
import com.github.pagehelper.PageInfo;

public interface TagService {
	List<Tag> listTag();

	PageInfo<Tag> getPageTagList(Integer pageIndex, Integer pageSize);

	void addTag(Tag tag);

	Tag getTagById(Integer tagId);

	void updateTag(Tag tag);

	void deleteTag(Integer tagId);
}
