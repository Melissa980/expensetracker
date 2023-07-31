package com.melissa.ExpenseTracker.dao.mappers;

        import com.melissa.ExpenseTracker.dto.Category;
        import org.springframework.jdbc.core.RowMapper;

        import java.sql.ResultSet;
        import java.sql.SQLException;

public class CategoryMapper implements RowMapper<Category> {
    @Override
    public Category mapRow(ResultSet rs, int rowNum) throws SQLException {
        Category category = new Category();
        category.setCategoryId(rs.getInt("categoryId"));
        category.setName(rs.getString("name"));
        return category;
    }
}
