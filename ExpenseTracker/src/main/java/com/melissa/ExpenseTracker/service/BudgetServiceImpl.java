package com.melissa.ExpenseTracker.service;

import com.melissa.ExpenseTracker.dao.BudgetDao;
import com.melissa.ExpenseTracker.dto.Budget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BudgetServiceImpl implements BudgetService {
    private final BudgetDao budgetDao;

    @Autowired
    public BudgetServiceImpl(BudgetDao budgetDao) {
        this.budgetDao = budgetDao;
    }

    @Override
    public void addBudget(Budget budget) {
        budgetDao.addBudget(budget);
    }

    @Override
    public Budget getBudgetById(int budgetId) {
        return budgetDao.getBudgetById(budgetId);
    }

    @Override
    public List<Budget> getAllBudgets() {
        return budgetDao.getAllBudgets();
    }

    @Override
    public void updateBudget(Budget budget) {
        budgetDao.updateBudget(budget);
    }

    @Override
    public void deleteBudget(int budgetId) {
        budgetDao.deleteBudget(budgetId);
    }
}
