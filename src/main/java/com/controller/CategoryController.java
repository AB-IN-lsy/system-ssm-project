package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.entity.Category;
import com.service.CategoryService;

@Controller
@RequestMapping("/category")
public class CategoryController {
	@Autowired
	private CategoryService categoryService;

	// ��ѯ���е��˷���,�� json��ʽ����
	@ResponseBody
	@RequestMapping("/listSub")
	public List<Category> getSubCategoryList(Integer parentId) {
		List<Category> categoryList = categoryService.listCategoryByParentId(parentId);
		return categoryList;
	}
}