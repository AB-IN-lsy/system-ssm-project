package com.service;

import java.util.List;

import com.entity.Category;

public interface CategoryService {
	/**
	 * 查出所有目录
	 * 
	 * @return
	 */
	List<Category> listCategory();

	/**
	 * 根据父级id,查询子分类信息
	 * 
	 * @return
	 */
	List<Category> listCategoryByParentId(Integer parentId);

	void addCategory(Category category);

	void deleteCategory(Integer categoryId);

	Category getCategoryById(Integer categoryId);

	void editCategory(Category category);

}
