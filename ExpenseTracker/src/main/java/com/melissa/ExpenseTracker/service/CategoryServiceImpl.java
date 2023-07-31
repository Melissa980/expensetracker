package com.melissa.ExpenseTracker.service;

import com.melissa.ExpenseTracker.dao.CategoryDao;
import com.melissa.ExpenseTracker.dto.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryDao categoryDao;

    @Autowired
    public CategoryServiceImpl(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    @Override
    public void addCategory(Category category) {
        categoryDao.addCategory(category);
    }

    @Override
    public Category getCategoryById(int categoryId) {
        return categoryDao.getCategoryById(categoryId);
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryDao.getAllCategories();
    }

    @Override
    public void updateCategory(Category category) {
        categoryDao.updateCategory(category);
    }

    @Override
    public void deleteCategory(int categoryId) {
        categoryDao.deleteCategory(categoryId);
    }
}
