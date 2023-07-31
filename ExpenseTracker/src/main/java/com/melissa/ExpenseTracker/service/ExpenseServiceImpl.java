package com.melissa.ExpenseTracker.service;

import com.melissa.ExpenseTracker.dao.ExpenseDao;
import com.melissa.ExpenseTracker.dto.Expense;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpenseServiceImpl implements ExpenseService {
    private final ExpenseDao expenseDao;

    @Autowired
    public ExpenseServiceImpl(ExpenseDao expenseDao) {
        this.expenseDao = expenseDao;
    }

    @Override
    public void addExpense(Expense expense) {
        expenseDao.addExpense(expense);
    }

    @Override
    public Expense getExpenseById(int expenseId) {
        return expenseDao.getExpenseById(expenseId);
    }

    @Override
    public List<Expense> getAllExpenses() {
        return expenseDao.getAllExpenses();
    }

    @Override
    public void updateExpense(Expense expense) {
        expenseDao.updateExpense(expense);
    }

    @Override
    public void deleteExpense(int expenseId) {
        expenseDao.deleteExpense(expenseId);
    }
}
