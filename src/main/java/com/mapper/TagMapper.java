package com.mapper;

import java.util.List;

import com.entity.Tag;

public interface TagMapper {

	List<Tag> listTag();

	void addTag(Tag tag);

	Tag getTagById(Integer tagId);

	void editTag(Tag tag);

	void deleteTag(Integer tagId);

}
