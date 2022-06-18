package com.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entity.Category;
import com.mapper.ArticleMapper;
import com.mapper.CategoryMapper;
import com.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryMapper categoryMapper;

	@Autowired
	private ArticleMapper articleMapper;

	// 查询所有的分类信息
	public List<Category> listCategory() {
		List<Category> categoryList = categoryMapper.listCategory();
		for (Category c : categoryList) {
			c.setArticleCount(articleMapper.countArticleByCategoryId(c.getCategoryId()));
		}
		return categoryList;
	}

	// 根据父级id,查询子分类信息
	public List<Category> listCategoryByParentId(Integer parentId) {
		return categoryMapper.listCategoryByParentId(parentId);
	}

	@Override
	public void addCategory(Category category) {
		// TODO Auto-generated method stub
		categoryMapper.addCategory(category);
	}

	@Override
	public void deleteCategory(Integer categoryId) {
		// TODO Auto-generated method stub
		categoryMapper.deleteCategory(categoryId);
	}

	@Override
	public Category getCategoryById(Integer categoryId) {
		// TODO Auto-generated method stub
		return categoryMapper.getCategoryById(categoryId);
	}

	@Override
	public void editCategory(Category category) {
		// TODO Auto-generated method stub
		categoryMapper.editCategory(category);
	}

}