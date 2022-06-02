package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.entity.Article;
import com.entity.Category;
import com.entity.Tag;
import com.github.pagehelper.PageInfo;
import com.service.ArticleService;
import com.service.CategoryService;
import com.service.TagService;

/**
 * 文章信息,控制层
 */
@Controller
@RequestMapping("/article")
public class ArticleController {
	@Autowired
	private ArticleService articleService;

	@Autowired
	private CategoryService cateGoryService; // 暂时还没创建

	@Autowired
	private TagService tagService; // 暂时还没创建

	/**
	 * 分页查询文章信息
	 * 
	 * @param pageIndex 用于分页,表示当前是第几页,默认是1
	 * @param pageSize  用于分页,表示每页有多少条数据,默认是10
	 * @param m
	 * @return 返回的是 PageInfo类型的数据,它里面含有分页信息,和具体的查出来的数据
	 */
	@RequestMapping(value = "")
	public String index(@RequestParam(required = false, defaultValue = "1") Integer pageIndex,
			@RequestParam(required = false, defaultValue = "10") Integer pageSize, ModelMap m) {

		// 分页查询文章相关的数据,放到作用域中 欠着
		PageInfo<Article> pageInfo = articleService.getPageArticleList(pageIndex, pageSize);

		m.put("pageUrlPrefix", "article?pageIndex"); // 把前缀传给分页的页面
		m.put("pageInfo", pageInfo);
		return "/Article/article-list";
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String goToAdd(ModelMap m) {
		// 分类信息
		List<Category> categoryList = cateGoryService.listCategory();

		// 标签信息
		List<Tag> tagList = tagService.listTag();

		// 放到作用域
		m.put("categoryList", categoryList);
		m.put("tagList", tagList);

		// 转到写文章的页面
		return "/Article/article-add";
	}
}