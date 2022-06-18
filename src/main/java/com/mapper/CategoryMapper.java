package com.mapper;

import java.util.List;

import com.entity.Category;

public interface CategoryMapper {

	List<Category> listCategory();

	List<Category> listCategoryByParentId(Integer parentId);

	void addCategory(Category category);

	void deleteCategory(Integer categoryId);

	Category getCategoryById(Integer categoryId);

	void editCategory(Category category);

}
