package com.melissa.ExpenseTracker.dao;

import com.melissa.ExpenseTracker.dao.mappers.BudgetMapper;
import com.melissa.ExpenseTracker.dto.Budget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BudgetDaoImpl implements BudgetDao {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BudgetDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void addBudget(Budget budget) {
        String sql = "INSERT INTO Budget (amount, categoryId, customerId) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, budget.getAmount(), budget.getCategoryId(), budget.getCustomerId());
    }

    @Override
    public Budget getBudgetById(int budgetId) {
        String sql = "SELECT * FROM Budget WHERE budgetId = ?";
        return jdbcTemplate.queryForObject(sql, new BudgetMapper(), budgetId);
    }

    @Override
    public List<Budget> getAllBudgets() {
        String sql = "SELECT * FROM Budget";
        return jdbcTemplate.query(sql, new BudgetMapper());
    }

    @Override
    public void updateBudget(Budget budget) {
        String sql = "UPDATE Budget SET amount = ?, categoryId = ?, customerId = ? WHERE budgetId = ?";
        jdbcTemplate.update(sql, budget.getAmount(), budget.getCategoryId(), budget.getCustomerId(), budget.getBudgetId());
    }

    @Override
    public void deleteBudget(int budgetId) {
        String sql = "DELETE FROM Budget WHERE budgetId = ?";
        jdbcTemplate.update(sql, budgetId);
    }
}
