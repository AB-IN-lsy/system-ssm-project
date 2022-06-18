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
	 * ��ѯ���з���
	 * 
	 * @param m
	 * @return
	 */
	@RequestMapping(value = "")
	public String index(ModelMap m) {

		// ��ҳ��ѯ������ص�����,�ŵ���������
		List<Category> categoryList = categoryService.listCategory();
		m.put("categoryList", categoryList);
		return "/Category/category-list";
	}

	/**
	 * ��ѯ���е��˷���,�� json��ʽ����
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
	 * ȥ�����ҳ
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
	 * ��ӷ���
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
	 * ����id,ɾ������
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
	 * ȥ���༭����ҳ��
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
	 * ���·���
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