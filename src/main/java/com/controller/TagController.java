package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.entity.Tag;
import com.github.pagehelper.PageInfo;
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
	 * ��ҳ��ѯ��ǩ��Ϣ
	 * 
	 * @param pageIndex ���ڷ�ҳ,��ʾ��ǰ�ǵڼ�ҳ,Ĭ����1
	 * @param pageSize  ���ڷ�ҳ,��ʾÿҳ�ж���������,Ĭ����10
	 * @param m
	 * @return ���ص��� PageInfo���͵�����,�����溬�з�ҳ��Ϣ,�;���Ĳ����������
	 */
	@RequestMapping(value = "")
	public String index(@RequestParam(required = false, defaultValue = "1") Integer pageIndex,
			@RequestParam(required = false, defaultValue = "10") Integer pageSize, ModelMap m) {

		// ��ҳ��ѯ������ص�����,�ŵ���������
		PageInfo<Tag> pageInfo = tagService.getPageTagList(pageIndex, pageSize);

		m.put("pageUrlPrefix", "tag?pageIndex"); // ��ǰ׺������ҳ��ҳ��
		m.put("pageInfo", pageInfo);
		return "/Tag/tag";
	}

	/**
	 * ��ӻ��޸ı�ǩ
	 * 
	 * @param tag
	 * @return
	 */
	@RequestMapping("/addOrUpdate")
	public String addOrUpdate(Tag tag) {
		// ����
		if (tag.getTagId() == null || tag.getTagId() == 0) {
			tagService.addTag(tag);
		} else {
			tagService.updateTag(tag);
		}

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
	public String editTag(@PathVariable("tagId") Integer tagId, ModelMap m) {
		Tag tag = tagService.getTagById(tagId);
		m.put("tag", tag);

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