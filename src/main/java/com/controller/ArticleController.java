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
 * ������Ϣ,���Ʋ�
 */
@Controller
@RequestMapping("/article")
public class ArticleController {
	@Autowired
	private ArticleService articleService;

	@Autowired
	private CategoryService cateGoryService; // ��ʱ��û����

	@Autowired
	private TagService tagService; // ��ʱ��û����

	/**
	 * ��ҳ��ѯ������Ϣ
	 * 
	 * @param pageIndex ���ڷ�ҳ,��ʾ��ǰ�ǵڼ�ҳ,Ĭ����1
	 * @param pageSize  ���ڷ�ҳ,��ʾÿҳ�ж���������,Ĭ����10
	 * @param m
	 * @return ���ص��� PageInfo���͵�����,�����溬�з�ҳ��Ϣ,�;���Ĳ����������
	 */
	@RequestMapping(value = "")
	public String index(@RequestParam(required = false, defaultValue = "1") Integer pageIndex,
			@RequestParam(required = false, defaultValue = "10") Integer pageSize, ModelMap m) {

		// ��ҳ��ѯ������ص�����,�ŵ��������� Ƿ��
		PageInfo<Article> pageInfo = articleService.getPageArticleList(pageIndex, pageSize);

		m.put("pageUrlPrefix", "article?pageIndex"); // ��ǰ׺������ҳ��ҳ��
		m.put("pageInfo", pageInfo);
		return "/Article/article-list";
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String goToAdd(ModelMap m) {
		// ������Ϣ
		List<Category> categoryList = cateGoryService.listCategory();

		// ��ǩ��Ϣ
		List<Tag> tagList = tagService.listTag();

		// �ŵ�������
		m.put("categoryList", categoryList);
		m.put("tagList", tagList);

		// ת��д���µ�ҳ��
		return "/Article/article-add";
	}
}