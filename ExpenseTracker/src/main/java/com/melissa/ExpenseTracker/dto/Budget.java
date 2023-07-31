package com.melissa.ExpenseTracker.dto;

import java.math.BigDecimal;

public class Budget {
    private int budgetId;
    private BigDecimal amount;
    private int categoryId;
    private int customerId;

    // Constructors
    public Budget() {
    }

    public Budget(int budgetId, BigDecimal amount, int categoryId, int customerId) {
        this.budgetId = budgetId;
        this.amount = amount;
        this.categoryId = categoryId;
        this.customerId = customerId;
    }

    // Getters and Setters
    public int getBudgetId() {
        return budgetId;
    }

    public void setBudgetId(int budgetId) {
        this.budgetId = budgetId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    // toString method (optional)
    @Override
    public String toString() {
        return "Budget [budgetId=" + budgetId + ", amount=" + amount + ", categoryId=" + categoryId + ", customerId=" + customerId + "]";
    }
}
