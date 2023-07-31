package com.melissa.ExpenseTracker.controller;

import com.melissa.ExpenseTracker.dto.Expense;
import com.melissa.ExpenseTracker.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/expenses")
public class ExpenseController {

    private final ExpenseService expenseService;

    @Autowired
    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @GetMapping
    public String displayExpenses(Model model) {
        List<Expense> expenses = expenseService.getAllExpenses();
        model.addAttribute("expenses", expenses);
        model.addAttribute("expense", new Expense());
        return "expenses"; // Assuming there's a "expenses" view template to display expenses
    }

    @GetMapping("addExpense")
    public String addExpenseForm(Model model) {
        model.addAttribute("expense", new Expense());
        return "addExpense"; // Assuming there's a "addExpense" view template for adding an expense
    }

    @PostMapping("addExpense")
    public String addExpense(@Valid Expense expense, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("expense", expense);
            return "addExpense"; // Return to the "addExpense" view template with validation errors
        }
        expenseService.addExpense(expense);
        return "redirect:/expenses"; // Redirect to the list of expenses after successful addition
    }

    @GetMapping("editExpense/{expenseId}")
    public String editExpenseForm(@PathVariable int expenseId, Model model) {
        Expense expense = expenseService.getExpenseById(expenseId);
        model.addAttribute("expense", expense);
        return "editExpense"; // Assuming there's a "editExpense" view template for editing an expense
    }

    @PostMapping("editExpense/{expenseId}")
    public String editExpense(@PathVariable int expenseId, @Valid Expense expense, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("expense", expense);
            return "editExpense"; // Return to the "editExpense" view template with validation errors
        }
        expense.setExpenseId(expenseId);
        expenseService.updateExpense(expense);
        return "redirect:/expenses"; // Redirect to the list of expenses after successful update
    }

    @GetMapping("deleteExpense/{expenseId}")
    public String deleteExpense(@PathVariable int expenseId) {
        expenseService.deleteExpense(expenseId);
        return "redirect:/expenses"; // Redirect to the list of expenses after successful deletion
    }

    @GetMapping("viewExpense/{expenseId}")
    public String viewExpenseDetails(@PathVariable int expenseId, Model model) {
        // Get the expense details from the service or DAO layer based on the expenseId
        Expense expense = expenseService.getExpenseById(expenseId);

        // Add the expense object to the model to be used by Thymeleaf in the template
        model.addAttribute("expense", expense);

        // Return the name of the Thymeleaf template to render (in this case, "view_expense.html")
        return "viewExpense";
    }
}
