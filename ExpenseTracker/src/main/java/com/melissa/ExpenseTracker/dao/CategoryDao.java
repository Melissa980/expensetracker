package com.melissa.ExpenseTracker.dao;

import com.melissa.ExpenseTracker.dto.Category;

import java.util.List;

public interface CategoryDao {
    void addCategory(Category category);
    Category getCategoryById(int categoryId);
    List<Category> getAllCategories();
    void updateCategory(Category category);
    void deleteCategory(int categoryId);
}
