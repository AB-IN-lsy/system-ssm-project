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

		// 分页查询文章相关的数据,放到作用域中
		PageInfo<Article> pageInfo = articleService.getPageArticleList(pageIndex, pageSize);

		m.put("pageUrlPrefix", "article?pageIndex"); // 把前缀传给分页的页面
		m.put("pageInfo", pageInfo);
		return "/Article/article-list";
	}

	/**
	 * 去往文章发布页
	 * 
	 * @param m
	 * @return
	 */
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

	/**
	 * 添加文章
	 * 
	 * @param request
	 * @return
	 */

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addArticle(HttpServletRequest request) {
		Article article = new Article();

		// 当前用户的id
		User user = (User) request.getSession().getAttribute("session_user");
		if (user != null)
			article.setArticleUserId(user.getUserId());

		// 文章标题 服务端永远取的都是name
		article.setArticleTitle(request.getParameter("articleTitle"));

		// 文章内容
		article.setArticleContent(request.getParameter("articleContent"));

		// 文章摘要
		String s = HtmlUtil.cleanHtmlTag(article.getArticleContent()); // hutool数据包，去除html标签
		article.setArticleSummary(s.length() > 150 ? s.substring(0, 150) : s);

		// 文章的发布时间,修改时间
		article.setArticleCreateTime(new Date());
		article.setArticleUpdateTime(new Date());

		article.setArticleCommentCount(0);
		article.setArticleLikeCount(0);
		article.setArticleViewCount(0);

		// 默认的排序
		article.setArticleOrder(1);

		// 文章的状态
		article.setArticleStatus(Integer.parseInt(request.getParameter("articleStatus")));

		// 填充分类
		List<Category> categoryList = new ArrayList<Category>(2);

		// 一级分类 添加了判断是否非空
		if (request.getParameter("articleParentCategoryId") != null) {
			int articleParentCategoryId = Integer.parseInt(request.getParameter("articleParentCategoryId"));
			categoryList.add(new Category(articleParentCategoryId));
		}
		// 二级分类
		if (request.getParameter("articleChildCategoryId") != null) {
			int articleChildCategoryId = Integer.parseInt(request.getParameter("articleChildCategoryId"));
			categoryList.add(new Category(articleChildCategoryId));
		}
		article.setCategoryList(categoryList);

		// 填充标签 添加了判断是否非空
		List<Tag> tagList = new ArrayList<>();
		if (request.getParameter("articleTagIds") != null) {
			String[] tagIds = request.getParameterValues("articleTagIds"); // 复选框都是同名，故用字符数组接取
			for (String tagId : tagIds) {
				tagList.add(new Tag(Integer.parseInt(tagId)));
			}
		}

		article.setTagList(tagList);

		// 数据准备好以后,调用业务层
		articleService.addArticle(article);

		// 转到文章列表页，相当于刷新一下
		return "forward:/article";
	}

	/**
	 * 添加草稿
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/addDraft", method = RequestMethod.POST)
	public String addDraft(HttpServletRequest request) {
		Article article = new Article();

		// 当前用户的id
		User user = (User) request.getSession().getAttribute("session_user");
		if (user != null)
			article.setArticleUserId(user.getUserId());

		// 文章标题 服务端永远取的都是name
		article.setArticleTitle(request.getParameter("articleTitle"));

		// 文章内容
		article.setArticleContent(request.getParameter("articleContent"));

		// 文章摘要
		String s = HtmlUtil.cleanHtmlTag(article.getArticleContent()); // hutool数据包，去除html标签
		article.setArticleSummary(s.length() > 150 ? s.substring(0, 150) : s);

		// 文章的发布时间,修改时间
		article.setArticleCreateTime(new Date());
		article.setArticleUpdateTime(new Date());

		article.setArticleCommentCount(0);
		article.setArticleLikeCount(0);
		article.setArticleViewCount(0);

		// 默认的排序
		article.setArticleOrder(1);

		// 文章的状态
		article.setArticleStatus(Integer.parseInt(request.getParameter("articleStatus")));

		// 填充分类
		List<Category> categoryList = new ArrayList<Category>(2);

		// 一级分类 添加了判断是否非空
		if (request.getParameter("articleParentCategoryId") != null) {
			int articleParentCategoryId = Integer.parseInt(request.getParameter("articleParentCategoryId"));
			categoryList.add(new Category(articleParentCategoryId));
		}
		// 二级分类
		if (request.getParameter("articleChildCategoryId") != null) {
			int articleChildCategoryId = Integer.parseInt(request.getParameter("articleChildCategoryId"));
			categoryList.add(new Category(articleChildCategoryId));
		}
		article.setCategoryList(categoryList);

		// 填充标签 添加了判断是否非空
		List<Tag> tagList = new ArrayList<>();
		if (request.getParameter("articleTagIds") != null) {
			String[] tagIds = request.getParameterValues("articleTagIds"); // 复选框都是同名，故用字符数组接取
			for (String tagId : tagIds) {
				tagList.add(new Tag(Integer.parseInt(tagId)));
			}
		}

		article.setTagList(tagList);

		// 数据准备好以后,调用业务层
		articleService.addArticle(article);

		// 转到主页
		return "forward:/user/index";
	}

	/**
	 * 上传图片
	 * 
	 * @param request
	 * @return
	 * @throws IllegalStateException
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping("/uploadImg")
	public String uploadArticleImg(MultipartHttpServletRequest request) throws IllegalStateException, IOException {
		// 得到客户端传过来的图片 , imgFile 是一个固定名称
		MultipartFile file = request.getFile("imgFile");

		// 随机生成一个文件名
		String fileName = UUID.randomUUID().toString() + ".jpg";

		// 定义一个存放文件的目标
		File destFile = new File("C:/Users/liusy/Pictures/uploadImg/" + fileName);

		// 把文件存到某个目录下
		file.transferTo(destFile);

		String path = "http://localhost:8080/upload/" + fileName;

		// 注意,这个 JSONObject 是来源于 hutool 工具包
		@SuppressWarnings("deprecation")
		JSONObject obj = JSONUtil.createObj().put("error", 0).put("url", path);

		return obj.toString();
	}

}