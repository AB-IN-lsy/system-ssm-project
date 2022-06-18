package com.service;

import java.util.List;

import com.entity.Tag;

public interface TagService {
	List<Tag> listTag();

	void addTag(Tag tag);

	Tag getTagById(Integer tagId);

	void editTag(Tag tag);

	void deleteTag(Integer tagId);
}
