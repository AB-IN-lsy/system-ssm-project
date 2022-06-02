package com.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entity.Tag;
import com.mapper.TagMapper;
import com.service.TagService;

@Service
public class TagServiceImpl implements TagService {

	@Autowired
	private TagMapper tagMapper;

	public List<Tag> listTag() {
		return tagMapper.listTag();
	}

}
