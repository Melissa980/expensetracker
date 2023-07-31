package com.melissa.ExpenseTracker.dao;

import com.melissa.ExpenseTracker.dto.Budget;

import java.util.List;

public interface BudgetDao {
    void addBudget(Budget budget);
    Budget getBudgetById(int budgetId);
    List<Budget> getAllBudgets();
    void updateBudget(Budget budget);
    void deleteBudget(int budgetId);
}

