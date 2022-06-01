package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.entity.Article;
import com.github.pagehelper.PageInfo;
import com.service.ArticleService;

/**
 * 文章信息,控制层
 */
@Controller
@RequestMapping("/article")
public class ArticleController {
	@Autowired
	private ArticleService articleService;

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
}