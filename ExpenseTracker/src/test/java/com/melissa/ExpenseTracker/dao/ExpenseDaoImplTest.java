/*
package com.melissa.ExpenseTracker.dao;

import com.melissa.ExpenseTracker.dao.mappers.ExpenseMapper;
import com.melissa.ExpenseTracker.dto.Expense;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@JdbcTest
@ActiveProfiles("test")
public class ExpenseDaoImplTest {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private ExpenseDao expenseDao;

    @BeforeEach
    public void setup() {
        expenseDao = new ExpenseDaoImpl(jdbcTemplate);
    }

    @Test
    public void testAddExpense() {
        Expense expense = new Expense();
        expense.setDescription("Grocery Shopping");
        expense.setAmount(new BigDecimal("100.00"));
        expense.setDate(Date.valueOf("2023-07-31"));
        expense.setCategoryId(1); // Replace with an existing category ID
        expense.setCustomerId(1); // Replace with an existing customer ID

        expenseDao.addExpense(expense);

        assertNotNull(expense.getExpenseId());
    }

    @Test
    public void testGetExpenseById() {
        int expenseId = 1; // Replace with an existing expense ID

        Expense expense = expenseDao.getExpenseById(expenseId);

        assertNotNull(expense);
        assertEquals(expenseId, expense.getExpenseId());
    }

    @Test
    public void testGetAllExpenses() {
        List<Expense> expenses = expenseDao.getAllExpenses();

        // Replace the following assertions with the actual expected number of expenses in the database
        assertNotNull(expenses);
        assertEquals(5, expenses.size());
    }

    @Test
    public void testUpdateExpense() {
        int expenseId = 1; // Replace with an existing expense ID

        Expense expense = expenseDao.getExpenseById(expenseId);
        assertNotNull(expense);

        BigDecimal newAmount = new BigDecimal("150.00");
        expense.setAmount(newAmount);

        expenseDao.updateExpense(expense);

        Expense updatedExpense = expenseDao.getExpenseById(expenseId);
        assertEquals(newAmount, updatedExpense.getAmount());
    }

    @Test
    public void testDeleteExpense() {
        int expenseId = 1; // Replace with an existing expense ID

        expenseDao.deleteExpense(expenseId);

        // Verify that the expense is no longer in the database
        Expense deletedExpense = expenseDao.getExpenseById(expenseId);
        assertNull(deletedExpense);
    }
}
*/