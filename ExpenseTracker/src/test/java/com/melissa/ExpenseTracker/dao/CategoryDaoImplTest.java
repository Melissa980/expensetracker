/*package com.melissa.ExpenseTracker.dao;

import com.melissa.ExpenseTracker.dao.mappers.CategoryMapper;
import com.melissa.ExpenseTracker.dto.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@JdbcTest
@ActiveProfiles("test")
public class CategoryDaoImplTest {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private CategoryDao categoryDao;

    @BeforeEach
    public void setup() {
        categoryDao = new CategoryDaoImpl(jdbcTemplate);
    }

    @Test
    public void testAddCategory() {
        Category category = new Category();
        category.setName("Groceries");

        categoryDao.addCategory(category);

        assertNotNull(category.getCategoryId());
    }

    @Test
    public void testGetCategoryById() {
        int categoryId = 1; // Replace with an existing category ID

        Category category = categoryDao.getCategoryById(categoryId);

        assertNotNull(category);
        assertEquals(categoryId, category.getCategoryId());
    }

    @Test
    public void testGetAllCategories() {
        List<Category> categories = categoryDao.getAllCategories();

        // Replace the following assertions with the actual expected number of categories in the database
        assertNotNull(categories);
        assertEquals(3, categories.size());
    }

    @Test
    public void testUpdateCategory() {
        int categoryId = 1; // Replace with an existing category ID

        Category category = categoryDao.getCategoryById(categoryId);
        assertNotNull(category);

        String newName = "Utilities";
        category.setName(newName);

        categoryDao.updateCategory(category);

        Category updatedCategory = categoryDao.getCategoryById(categoryId);
        assertEquals(newName, updatedCategory.getName());
    }

    @Test
    public void testDeleteCategory() {
        int categoryId = 1; // Replace with an existing category ID

        categoryDao.deleteCategory(categoryId);

        // Verify that the category is no longer in the database
        Category deletedCategory = categoryDao.getCategoryById(categoryId);
        assertNull(deletedCategory);
    }
}
*/