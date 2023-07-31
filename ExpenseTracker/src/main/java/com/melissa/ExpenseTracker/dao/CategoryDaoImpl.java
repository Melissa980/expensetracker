package com.melissa.ExpenseTracker.dao;

        import com.melissa.ExpenseTracker.dao.mappers.CategoryMapper;
        import com.melissa.ExpenseTracker.dto.Category;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.jdbc.core.JdbcTemplate;
        import org.springframework.stereotype.Repository;

        import java.util.List;

@Repository
public class CategoryDaoImpl implements CategoryDao {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public CategoryDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void addCategory(Category category) {
        String sql = "INSERT INTO Category (name) VALUES (?)";
        jdbcTemplate.update(sql, category.getName());
    }

    @Override
    public Category getCategoryById(int categoryId) {
        String sql = "SELECT * FROM Category WHERE categoryId = ?";
        return jdbcTemplate.queryForObject(sql, new CategoryMapper(), categoryId);
    }

    @Override
    public List<Category> getAllCategories() {
        String sql = "SELECT * FROM Category";
        return jdbcTemplate.query(sql, new CategoryMapper());
    }

    @Override
    public void updateCategory(Category category) {
        String sql = "UPDATE Category SET name = ? WHERE categoryId = ?";
        jdbcTemplate.update(sql, category.getName(), category.getCategoryId());
    }

    @Override
    public void deleteCategory(int categoryId) {
        String sql = "DELETE FROM Category WHERE categoryId = ?";
        jdbcTemplate.update(sql, categoryId);
    }
}
