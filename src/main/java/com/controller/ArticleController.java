package com.controller;

import java.io.File;
import java.io.IOException;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.entity.Article;
import com.entity.Category;
import com.entity.Tag;
import com.entity.User;
import com.github.pagehelper.PageInfo;
import com.service.ArticleService;
import com.service.CategoryService;
import com.service.TagService;

import cn.hutool.core.lang.UUID;
import cn.hutool.http.HtmlUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;

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

	/**
	 * ȥ�����·���ҳ
	 * 
	 * @param m
	 * @return
	 */
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

	/**
	 * �������
	 * 
	 * @param request
	 * @return
	 */

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addArticle(HttpServletRequest request) {
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

		// ������
		List<Category> categoryList = new ArrayList<Category>(2);

		// һ������ ������ж��Ƿ�ǿ�
		if (request.getParameter("articleParentCategoryId") != null) {
			int articleParentCategoryId = Integer.parseInt(request.getParameter("articleParentCategoryId"));
			categoryList.add(new Category(articleParentCategoryId));
		}
		// ��������
		if (request.getParameter("articleChildCategoryId") != null) {
			int articleChildCategoryId = Integer.parseInt(request.getParameter("articleChildCategoryId"));
			categoryList.add(new Category(articleChildCategoryId));
		}
		article.setCategoryList(categoryList);

		// ����ǩ ������ж��Ƿ�ǿ�
		List<Tag> tagList = new ArrayList<>();
		if (request.getParameter("articleTagIds") != null) {
			String[] tagIds = request.getParameterValues("articleTagIds"); // ��ѡ����ͬ���������ַ������ȡ
			for (String tagId : tagIds) {
				tagList.add(new Tag(Integer.parseInt(tagId)));
			}
		}

		article.setTagList(tagList);

		// ����׼�����Ժ�,����ҵ���
		articleService.addArticle(article);

		// ת�������б�ҳ���൱��ˢ��һ��
		return "forward:/article";
	}

	/**
	 * ��Ӳݸ�
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/addDraft", method = RequestMethod.POST)
	public String addDraft(HttpServletRequest request) {
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

		// ������
		List<Category> categoryList = new ArrayList<Category>(2);

		// һ������ ������ж��Ƿ�ǿ�
		if (request.getParameter("articleParentCategoryId") != null) {
			int articleParentCategoryId = Integer.parseInt(request.getParameter("articleParentCategoryId"));
			categoryList.add(new Category(articleParentCategoryId));
		}
		// ��������
		if (request.getParameter("articleChildCategoryId") != null) {
			int articleChildCategoryId = Integer.parseInt(request.getParameter("articleChildCategoryId"));
			categoryList.add(new Category(articleChildCategoryId));
		}
		article.setCategoryList(categoryList);

		// ����ǩ ������ж��Ƿ�ǿ�
		List<Tag> tagList = new ArrayList<>();
		if (request.getParameter("articleTagIds") != null) {
			String[] tagIds = request.getParameterValues("articleTagIds"); // ��ѡ����ͬ���������ַ������ȡ
			for (String tagId : tagIds) {
				tagList.add(new Tag(Integer.parseInt(tagId)));
			}
		}

		article.setTagList(tagList);

		// ����׼�����Ժ�,����ҵ���
		articleService.addArticle(article);

		// ת����ҳ
		return "forward:/user/index";
	}

	/**
	 * �ϴ�ͼƬ
	 * 
	 * @param request
	 * @return
	 * @throws IllegalStateException
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping("/uploadImg")
	public String uploadArticleImg(MultipartHttpServletRequest request) throws IllegalStateException, IOException {
		// �õ��ͻ��˴�������ͼƬ , imgFile ��һ���̶�����
		MultipartFile file = request.getFile("imgFile");

		// �������һ���ļ���
		String fileName = UUID.randomUUID().toString() + ".jpg";

		// ����һ������ļ���Ŀ��
		File destFile = new File("C:/Users/liusy/Pictures/uploadImg/" + fileName);

		// ���ļ��浽ĳ��Ŀ¼��
		file.transferTo(destFile);

		String path = "http://localhost:8080/upload/" + fileName;

		// ע��,��� JSONObject ����Դ�� hutool ���߰�
		@SuppressWarnings("deprecation")
		JSONObject obj = JSONUtil.createObj().put("error", 0).put("url", path);

		return obj.toString();
	}

}