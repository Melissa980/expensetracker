package com.melissa.ExpenseTracker.dao.mappers;

import com.melissa.ExpenseTracker.dto.Expense;
import org.springframework.jdbc.core.RowMapper;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ExpenseMapper implements RowMapper<Expense> {
    @Override
    public Expense mapRow(ResultSet rs, int rowNum) throws SQLException {
        Expense expense = new Expense();
        expense.setExpenseId(rs.getInt("expenseId"));
        expense.setDescription(rs.getString("description"));
        expense.setAmount(rs.getBigDecimal("amount"));
        expense.setDate(rs.getDate("date"));
        expense.setCategoryId(rs.getInt("categoryId"));
        expense.setCustomerId(rs.getInt("customerId"));
        return expense;
    }
}
