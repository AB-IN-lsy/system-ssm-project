package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.entity.Tag;
import com.service.ArticleService;
import com.service.TagService;

@Controller
@RequestMapping("/tag")
public class TagController {
	@Autowired
	TagService tagService;

	@Autowired
	ArticleService articleService;

	/**
	 * 查询所有标签
	 * 
	 * @param m
	 * @return
	 */
	@RequestMapping(value = "")
	public String index(ModelMap m) {
		List<Tag> tagList = tagService.listTag();
		m.put("tagList", tagList);
		return "/Tag/tag-list";
	}

	/**
	 * 添加或修改标签
	 * 
	 * @param tag
	 * @return
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addTag(Tag tag) {
		// 新增
		tagService.addTag(tag);
		return "forward:/tag";

	}

	/**
	 * 根据id,查出tag信息,转到修改页面
	 * 
	 * @param tagId
	 * @param m
	 * @return
	 */
	@RequestMapping("/edit/{tagId}")
	public String goToEdit(@PathVariable("tagId") Integer tagId, ModelMap m) {
		List<Tag> tagList = tagService.listTag();
		m.put("tagList", tagList);

		Tag tag = tagService.getTagById(tagId);
		m.put("tag", tag);

		return "/Tag/tag-edit";
	}

	/**
	 * 根据id, 更新tag
	 * 
	 * @param tag
	 * @return
	 */
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public String editTag(Tag tag) {
		tagService.editTag(tag);
		return "forward:/tag";
	}

	/**
	 * 根据id,删除tag
	 * 
	 * @param tagId
	 * @return
	 */
	@RequestMapping("/delete/{tagId}")
	public String deleteTag(@PathVariable("tagId") Integer tagId) {
		Integer count = articleService.countArticleByTagId(tagId);
		if (count == 0) {
			tagService.deleteTag(tagId);
		}

		return "forward:/tag";
	}

}