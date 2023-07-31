package com.melissa.ExpenseTracker.dao;

import com.melissa.ExpenseTracker.dao.mappers.ExpenseMapper;
import com.melissa.ExpenseTracker.dto.Expense;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ExpenseDaoImpl implements ExpenseDao {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ExpenseDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void addExpense(Expense expense) {
        String sql = "INSERT INTO Expense (description, amount, date, categoryId, customerId) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, expense.getDescription(), expense.getAmount(), expense.getDate(), expense.getCategoryId(), expense.getCustomerId());
    }

    @Override
    public Expense getExpenseById(int expenseId) {
        String sql = "SELECT * FROM Expense WHERE expenseId = ?";
        return jdbcTemplate.queryForObject(sql, new ExpenseMapper(), expenseId);
    }

    @Override
    public List<Expense> getAllExpenses() {
        String sql = "SELECT * FROM Expense";
        return jdbcTemplate.query(sql, new ExpenseMapper());
    }

    @Override
    public void updateExpense(Expense expense) {
        String sql = "UPDATE Expense SET description = ?, amount = ?, date = ?, categoryId = ?, customerId = ? WHERE expenseId = ?";
        jdbcTemplate.update(sql, expense.getDescription(), expense.getAmount(), expense.getDate(),
                expense.getCategoryId(), expense.getCustomerId(), expense.getExpenseId());
    }

    @Override
    public void deleteExpense(int expenseId) {
        String sql = "DELETE FROM Expense WHERE expenseId = ?";
        jdbcTemplate.update(sql, expenseId);
    }
}
