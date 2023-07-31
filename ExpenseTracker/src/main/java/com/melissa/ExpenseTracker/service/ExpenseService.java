package com.melissa.ExpenseTracker.service;

import com.melissa.ExpenseTracker.dto.Expense;

import java.util.List;

public interface ExpenseService {
    void addExpense(Expense expense);
    Expense getExpenseById(int expenseId);
    List<Expense> getAllExpenses();
    void updateExpense(Expense expense);
    void deleteExpense(int expenseId);
}
