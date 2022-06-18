package com.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entity.Tag;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mapper.ArticleMapper;
import com.mapper.TagMapper;
import com.service.TagService;

@Service
public class TagServiceImpl implements TagService {

	@Autowired
	private TagMapper tagMapper;

	@Autowired
	private ArticleMapper articleMapper;

	public List<Tag> listTag() {
		return tagMapper.listTag();
	}

	public PageInfo<Tag> getPageTagList(Integer pageIndex, Integer pageSize) {
		PageHelper.startPage(pageIndex, pageSize);

		List<Tag> tagList = tagMapper.listTag();
		for (Tag t : tagList) {
			t.setArticleCount(articleMapper.countArticleBytagId(t.getTagId()));
		}

		return new PageInfo<Tag>(tagList);
	}

	@Override
	public void addTag(Tag tag) {
		// TODO Auto-generated method stub
		tagMapper.addTag(tag);
	}

	@Override
	public Tag getTagById(Integer tagId) {
		// TODO Auto-generated method stub
		return tagMapper.getTagById(tagId);

	}

	@Override
	public void updateTag(Tag tag) {
		// TODO Auto-generated method stub
		tagMapper.updateTag(tag);

	}

	@Override
	public void deleteTag(Integer tagId) {
		// TODO Auto-generated method stub
		tagMapper.deleteTag(tagId);
	}

}
