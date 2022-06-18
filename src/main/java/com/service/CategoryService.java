package com.service;

import java.util.List;

import com.entity.Category;

public interface CategoryService {
	/**
	 * �������Ŀ¼
	 * 
	 * @return
	 */
	List<Category> listCategory();

	/**
	 * ���ݸ���id,��ѯ�ӷ�����Ϣ
	 * 
	 * @return
	 */
	List<Category> listCategoryByParentId(Integer parentId);

	void addCategory(Category category);

	void deleteCategory(Integer categoryId);

	Category getCategoryById(Integer categoryId);

	void editCategory(Category category);

}
