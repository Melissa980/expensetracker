package com.melissa.ExpenseTracker.dao.mappers;

import com.melissa.ExpenseTracker.dto.Budget;
import org.springframework.jdbc.core.RowMapper;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BudgetMapper implements RowMapper<Budget> {
    @Override
    public Budget mapRow(ResultSet rs, int rowNum) throws SQLException {
        Budget budget = new Budget();
        budget.setBudgetId(rs.getInt("budgetId"));
        budget.setAmount(rs.getBigDecimal("amount"));
        budget.setCategoryId(rs.getInt("categoryId"));
        budget.setCustomerId(rs.getInt("customerId"));
        return budget;
    }
}
