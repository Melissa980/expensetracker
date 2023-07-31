/* package com.melissa.ExpenseTracker.dao;

import com.melissa.ExpenseTracker.dao.mappers.BudgetMapper;
import com.melissa.ExpenseTracker.dto.Budget;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@JdbcTest
@ActiveProfiles("test")
public class BudgetDaoImplTest {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private BudgetDao budgetDao;

    @BeforeEach
    public void setup() {
        budgetDao = new BudgetDaoImpl(jdbcTemplate);
    }

    @Test
    public void testAddBudget() {
        Budget budget = new Budget();
        budget.setAmount(new BigDecimal("500.00"));
        budget.setCategoryId(1); // Replace with an existing category ID
        budget.setCustomerId(1); // Replace with an existing customer ID

        budgetDao.addBudget(budget);

        assertNotNull(budget.getBudgetId());
    }

    @Test
    public void testGetBudgetById() {
        int budgetId = 1; // Replace with an existing budget ID

        Budget budget = budgetDao.getBudgetById(budgetId);

        assertNotNull(budget);
        assertEquals(budgetId, budget.getBudgetId());
    }

    @Test
    public void testGetAllBudgets() {
        List<Budget> budgets = budgetDao.getAllBudgets();

        // Replace the following assertions with the actual expected number of budgets in the database
        assertNotNull(budgets);
        assertEquals(2, budgets.size());
    }

    @Test
    public void testUpdateBudget() {
        int budgetId = 1; // Replace with an existing budget ID

        Budget budget = budgetDao.getBudgetById(budgetId);
        assertNotNull(budget);

        BigDecimal newAmount = new BigDecimal("800.00");
        budget.setAmount(newAmount);

        budgetDao.updateBudget(budget);

        Budget updatedBudget = budgetDao.getBudgetById(budgetId);
        assertEquals(newAmount, updatedBudget.getAmount());
    }

    @Test
    public void testDeleteBudget() {
        int budgetId = 1; // Replace with an existing budget ID

        budgetDao.deleteBudget(budgetId);

        // Verify that the budget is no longer in the database
        List<Budget> budgets = budgetDao.getAllBudgets();
        for (Budget budget : budgets) {
            assertNotEquals(budgetId, budget.getBudgetId());
        }
    }
}
*/