package com.melissa.ExpenseTracker.controller;

import com.melissa.ExpenseTracker.dto.Customer;
import com.melissa.ExpenseTracker.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public String displayCustomers(Model model) {
        List<Customer> customers = customerService.getAllCustomers();
        model.addAttribute("customers", customers);
        return "customers"; // Assuming there's a "customers" view template to display customers
    }

    @GetMapping("addCustomer")
    public String addCustomerForm(Model model) {
        model.addAttribute("customer", new Customer());
        return "addCustomer"; // Assuming there's a "addCustomer" view template for adding a customer
    }

    @PostMapping("addCustomer")
    public String addCustomer(@Valid Customer customer, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("customer", customer);
            return "addCustomer"; // Return to the "addCustomer" view template with validation errors
        }
        customerService.addCustomer(customer);
        return "redirect:/customers"; // Redirect to the list of customers after successful addition
    }

    @GetMapping("editCustomer/{customerId}")
    public String editCustomerForm(@PathVariable int customerId, Model model) {
        Customer customer = customerService.getCustomerById(customerId);
        model.addAttribute("customer", customer);
        return "editCustomer"; // Assuming there's a "editCustomer" view template for editing a customer
    }

    @PostMapping("editCustomer/{customerId}")
    public String editCustomer(@PathVariable int customerId, @Valid Customer customer, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("customer", customer);
            return "editCustomer"; // Return to the "editCustomer" view template with validation errors
        }
        customer.setCustomerId(customerId);
        customerService.updateCustomer(customer);
        return "redirect:/customers"; // Redirect to the list of customers after successful update
    }

    @GetMapping("deleteCustomer/{customerId}")
    public String deleteCustomer(@PathVariable int customerId) {
        customerService.deleteCustomer(customerId);
        return "redirect:/customers"; // Redirect to the list of customers after successful deletion
    }
}
