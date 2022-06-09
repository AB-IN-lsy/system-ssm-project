package com.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.entity.Article;
import com.entity.Category;
import com.entity.Tag;
import com.entity.User;
import com.github.pagehelper.PageInfo;
import com.service.ArticleService;
import com.service.CategoryService;
import com.service.TagService;

import cn.hutool.http.HtmlUtil;

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

		// ��ҳ��ѯ������ص�����,�ŵ���������
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

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String add(HttpServletRequest request) {

		Article article = new Article();

		// ��ǰ�û���id
		User user = (User) request.getSession().getAttribute("session_user");
		if (user != null)
			article.setArticleUserId(user.getUserId());

		// ���±��� �������Զȡ�Ķ���name
		article.setArticleTitle(request.getParameter("articleTitle"));

		// ��������
		article.setArticleContent(request.getParameter("articleContent"));

		// ����ժҪ
		String s = HtmlUtil.cleanHtmlTag(article.getArticleContent()); // hutool���ݰ���ȥ��html��ǩ
		article.setArticleSummary(s.length() > 150 ? s.substring(0, 150) : s);

		// ���µķ���ʱ��,�޸�ʱ��
		article.setArticleCreateTime(new Date());
		article.setArticleUpdateTime(new Date());

		article.setArticleCommentCount(0);
		article.setArticleLikeCount(0);
		article.setArticleViewCount(0);

		// Ĭ�ϵ�����
		article.setArticleOrder(1);

		// ���µ�״̬
		article.setArticleStatus(Integer.parseInt(request.getParameter("articleStatus")));

		// һ������
		int articleParentCategoryId = Integer.parseInt(request.getParameter("articleParentCategoryId"));

		// ��������
		int articleChildCategoryId = Integer.parseInt(request.getParameter("articleChildCategoryId"));

		List<Category> categoryList = new ArrayList<Category>(2);
		categoryList.add(new Category(articleParentCategoryId));
		categoryList.add(new Category(articleChildCategoryId));
		article.setCategoryList(categoryList);

		// ��ǩ
		String[] tagIds = request.getParameterValues("articleTagIds"); // ��ѡ����ͬ���������ַ������ȡ
		List<Tag> tagList = new ArrayList<>();
		for (String tagId : tagIds) {
			tagList.add(new Tag(Integer.parseInt(tagId)));
		}
		article.setTagList(tagList);

		// ����׼�����Ժ�,����ҵ���
		articleService.add(article);

		// ת�������б�ҳ���൱��ˢ��һ��
		return "forward:/article";

	}
}