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
	 * ��ѯ���б�ǩ
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
	 * ��ӻ��޸ı�ǩ
	 * 
	 * @param tag
	 * @return
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addTag(Tag tag) {
		// ����
		tagService.addTag(tag);
		return "forward:/tag";

	}

	/**
	 * ����id,���tag��Ϣ,ת���޸�ҳ��
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
	 * ����id, ����tag
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
	 * ����id,ɾ��tag
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