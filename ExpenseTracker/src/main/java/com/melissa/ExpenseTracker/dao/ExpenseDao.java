package com.melissa.ExpenseTracker.dao;

import com.melissa.ExpenseTracker.dto.Expense;

import java.util.List;

public interface ExpenseDao {
    void addExpense(Expense expense);
    Expense getExpenseById(int expenseId);
    List<Expense> getAllExpenses();
    void updateExpense(Expense expense);
    void deleteExpense(int expenseId);
}
