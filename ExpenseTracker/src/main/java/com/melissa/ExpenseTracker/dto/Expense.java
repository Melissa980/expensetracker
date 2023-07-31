package com.melissa.ExpenseTracker.dto;

import java.math.BigDecimal;
import java.sql.Date;

public class Expense {
    private int expenseId;
    private String description;
    private BigDecimal amount;
    private Date date;
    private int categoryId;
    private int customerId;

    // Constructors
    public Expense() {
    }

    public Expense(int expenseId, String description, BigDecimal amount, Date date, int categoryId, int customerId) {
        this.expenseId = expenseId;
        this.description = description;
        this.amount = amount;
        this.date = date;
        this.categoryId = categoryId;
        this.customerId = customerId;
    }

    // Getters and Setters
    public int getExpenseId() {
        return expenseId;
    }

    public void setExpenseId(int expenseId) {
        this.expenseId = expenseId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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
        return "Expense [expenseId=" + expenseId + ", description=" + description + ", amount=" + amount +
                ", date=" + date + ", categoryId=" + categoryId + ", customerId=" + customerId + "]";
    }
}
