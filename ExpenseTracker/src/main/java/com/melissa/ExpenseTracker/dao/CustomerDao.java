package com.melissa.ExpenseTracker.dao;

import com.melissa.ExpenseTracker.dto.Customer;

import java.util.List;

public interface CustomerDao {
    // CRUD operations
    void addCustomer(Customer customer);
    Customer getCustomerById(int customerId);
    List<Customer> getAllCustomers();
    void updateCustomer(Customer customer);
    void deleteCustomer(int customerId);
}
