package com.mapper;

import java.util.List;

import com.entity.Tag;

public interface TagMapper {

	List<Tag> listTag();

	void addTag(Tag tag);

	Tag getTagById(Integer tagId);

	void updateTag(Tag tag);

	void deleteTag(Integer tagId);

}
