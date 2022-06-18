package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.entity.Category;
import com.service.ArticleService;
import com.service.CategoryService;

@Controller
@RequestMapping("/category")
public class CategoryController {
	@Autowired
	private CategoryService categoryService;

	@Autowired
	ArticleService articleService;

	/**
	 * 查询所有分类
	 * 
	 * @param m
	 * @return
	 */
	@RequestMapping(value = "")
	public String index(ModelMap m) {

		// 分页查询文章相关的数据,放到作用域中
		List<Category> categoryList = categoryService.listCategory();
		m.put("categoryList", categoryList);
		return "/Category/category-list";
	}

	/**
	 * 查询所有的了分类,以 json格式返回
	 * 
	 * @param parentId
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/listSub")
	public List<Category> getSubCategoryList(Integer parentId) {
		List<Category> categoryList = categoryService.listCategoryByParentId(parentId);
		return categoryList;
	}

	/**
	 * 去往添加页
	 * 
	 * @param m
	 * @return
	 */
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String goToAdd(ModelMap m) {
		List<Category> categoryList = categoryService.listCategory();
		m.put("categoryList", categoryList);
		return "/Category/category-list";
	}

	/**
	 * 添加分类
	 * 
	 * @param category
	 * @return
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addCategory(Category category) {
		categoryService.addCategory(category);
		return "forward:/category";
	}

	/**
	 * 根据id,删除分类
	 * 
	 * @param categoryId
	 * @return
	 */
	@RequestMapping("/delete/{categoryId}")
	public String deleteCategory(@PathVariable("categoryId") Integer categoryId) {
		Integer count = articleService.countArticleByCategoryId(categoryId);
		if (count == 0) {
			categoryService.deleteCategory(categoryId);
		}

		return "forward:/category";
	}

	/**
	 * 去往编辑分类页面
	 *
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/edit/{categoryId}")
	public String goToEdit(@PathVariable("categoryId") Integer categoryId, ModelMap m) {

		List<Category> categoryList = categoryService.listCategory();
		m.put("categoryList", categoryList);

		Category category = categoryService.getCategoryById(categoryId);
		m.put("category", category);

		return "/Category/category-edit";
	}

	/**
	 * 更新分类
	 * 
	 * @param category
	 * @return
	 */
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public String editCategory(Category category) {

		categoryService.editCategory(category);
		return "forward:/category";
	}

}