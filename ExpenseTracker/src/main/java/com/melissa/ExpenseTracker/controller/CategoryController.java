package com.melissa.ExpenseTracker.controller;

import com.melissa.ExpenseTracker.dto.Category;
import com.melissa.ExpenseTracker.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public String displayCategories(Model model) {
        List<Category> categories = categoryService.getAllCategories();
        model.addAttribute("categories", categories);
        model.addAttribute("category", new Category());
        return "categories"; // Assuming there's a "categories" view template to display categories
    }

    @GetMapping("addCategory")
    public String addCategoryForm(Model model) {
        model.addAttribute("category", new Category());
        return "addCategory"; // Assuming there's a "addCategory" view template for adding a category
    }

    @PostMapping("addCategory")
    public String addCategory(@Valid Category category, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("category", category);
            return "addCategory"; // Return to the "addCategory" view template with validation errors
        }
        categoryService.addCategory(category);
        return "redirect:/categories"; // Redirect to the list of categories after successful addition
    }

    @GetMapping("editCategory/{categoryId}")
    public String editCategoryForm(@PathVariable int categoryId, Model model) {
        Category category = categoryService.getCategoryById(categoryId);
        model.addAttribute("category", category);
        return "editCategory"; // Assuming there's a "editCategory" view template for editing a category
    }

    @PostMapping("editCategory/{categoryId}")
    public String editCategory(@PathVariable int categoryId, @Valid Category category, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("category", category);
            return "editCategory"; // Return to the "editCategory" view template with validation errors
        }
        category.setCategoryId(categoryId);
        categoryService.updateCategory(category);
        return "redirect:/categories"; // Redirect to the list of categories after successful update
    }

    @GetMapping("deleteCategory/{categoryId}")
    public String deleteCategory(@PathVariable int categoryId) {
        categoryService.deleteCategory(categoryId);
        return "redirect:/categories"; // Redirect to the list of categories after successful deletion
    }

    @GetMapping("viewCategory/{categoryId}")
    public String viewCategoryDetails(@PathVariable int categoryId, Model model) {
        // Get the category details from the service or DAO layer based on the categoryId
        Category category = categoryService.getCategoryById(categoryId);

        // Add the category object to the model to be used by Thymeleaf in the template
        model.addAttribute("category", category);

        return "viewCategory";
    }
}
