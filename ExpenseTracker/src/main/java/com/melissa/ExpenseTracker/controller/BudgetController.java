package com.melissa.ExpenseTracker.controller;

import com.melissa.ExpenseTracker.dto.Budget;
import com.melissa.ExpenseTracker.service.BudgetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/budgets")
public class BudgetController {

    private final BudgetService budgetService;

    @Autowired
    public BudgetController(BudgetService budgetService) {
        this.budgetService = budgetService;
    }

    @GetMapping
    public String displayBudgets(Model model) {
        List<Budget> budgets = budgetService.getAllBudgets();
        model.addAttribute("budgets", budgets);
        return "budgets"; // Assuming there's a "budgets" view template to display budgets
    }

    @GetMapping("addBudget")
    public String addBudgetForm(Model model) {
        model.addAttribute("budget", new Budget());
        return "addBudget"; // Assuming there's a "addBudget" view template for adding a budget
    }

    @PostMapping("addBudget")
    public String addBudget(@Valid Budget budget, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("budget", budget);
            return "addBudget"; // Return to the "addBudget" view template with validation errors
        }
        budgetService.addBudget(budget);
        return "redirect:/budgets"; // Redirect to the list of budgets after successful addition
    }

    @GetMapping("editBudget/{budgetId}")
    public String editBudgetForm(@PathVariable int budgetId, Model model) {
        Budget budget = budgetService.getBudgetById(budgetId);
        model.addAttribute("budget", budget);
        return "editBudget"; // Assuming there's a "editBudget" view template for editing a budget
    }

    @PostMapping("editBudget/{budgetId}")
    public String editBudget(@PathVariable int budgetId, @Valid Budget budget, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("budget", budget);
            return "editBudget"; // Return to the "editBudget" view template with validation errors
        }
        budget.setBudgetId(budgetId);
        budgetService.updateBudget(budget);
        return "redirect:/budgets"; // Redirect to the list of budgets after successful update
    }

    @GetMapping("deleteBudget/{budgetId}")
    public String deleteBudget(@PathVariable int budgetId) {
        budgetService.deleteBudget(budgetId);
        return "redirect:/budgets"; // Redirect to the list of budgets after successful deletion
    }

    @GetMapping("viewBudget/{budgetId}")
    public String viewBudgetDetails(@PathVariable int budgetId, Model model) {
        // Get the budget details from the service or DAO layer based on the budgetId
        Budget budget = budgetService.getBudgetById(budgetId);

        // Add the budget object to the model to be used by Thymeleaf in the template
        model.addAttribute("budget", budget);

        // Return the name of the Thymeleaf template to render (in this case, "view_budget.html")
        return "viewBudget";
    }
}
