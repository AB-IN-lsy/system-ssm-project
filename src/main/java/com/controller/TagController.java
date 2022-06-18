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
	 * 分页查询标签信息
	 * 
	 * @param pageIndex 用于分页,表示当前是第几页,默认是1
	 * @param pageSize  用于分页,表示每页有多少条数据,默认是10
	 * @param m
	 * @return 返回的是 PageInfo类型的数据,它里面含有分页信息,和具体的查出来的数据
	 */
	@RequestMapping(value = "")
	public String index(@RequestParam(required = false, defaultValue = "1") Integer pageIndex,
			@RequestParam(required = false, defaultValue = "10") Integer pageSize, ModelMap m) {

		// 分页查询文章相关的数据,放到作用域中
		PageInfo<Tag> pageInfo = tagService.getPageTagList(pageIndex, pageSize);

		m.put("pageUrlPrefix", "tag?pageIndex"); // 把前缀传给分页的页面
		m.put("pageInfo", pageInfo);
		return "/Tag/tag";
	}

	/**
	 * 添加或修改标签
	 * 
	 * @param tag
	 * @return
	 */
	@RequestMapping("/addOrUpdate")
	public String addOrUpdate(Tag tag) {
		// 新增
		if (tag.getTagId() == null || tag.getTagId() == 0) {
			tagService.addTag(tag);
		} else {
			tagService.updateTag(tag);
		}

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
	public String editTag(@PathVariable("tagId") Integer tagId, ModelMap m) {
		Tag tag = tagService.getTagById(tagId);
		m.put("tag", tag);

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