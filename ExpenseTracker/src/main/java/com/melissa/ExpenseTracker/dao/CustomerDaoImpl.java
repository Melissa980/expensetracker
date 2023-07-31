package com.melissa.ExpenseTracker.dao;

import java.util.List;

import com.melissa.ExpenseTracker.dao.mappers.CustomerMapper;
import com.melissa.ExpenseTracker.dto.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CustomerDaoImpl implements CustomerDao {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public CustomerDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    @Override
    public void addCustomer(Customer customer) {
        String sql = "INSERT INTO Customer (firstName, lastName, email) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, customer.getFirstName(), customer.getLastName(), customer.getEmail());
    }

    @Override
    public Customer getCustomerById(int customerId) {
        String sql = "SELECT * FROM Customer WHERE customerId = ?";
        return jdbcTemplate.queryForObject(sql, new CustomerMapper(), customerId);
    }

    @Override
    public List<Customer> getAllCustomers() {
        String sql = "SELECT * FROM Customer";
        return jdbcTemplate.query(sql, new CustomerMapper());
    }

    @Override
    public void updateCustomer(Customer customer) {
        String sql = "UPDATE Customer SET firstName = ?, lastName = ?, email = ? WHERE customerId = ?";
        jdbcTemplate.update(sql, customer.getFirstName(), customer.getLastName(), customer.getEmail(), customer.getCustomerId());
    }

    @Override
    public void deleteCustomer(int customerId) {
        String sql = "DELETE FROM Customer WHERE customerId = ?";
        jdbcTemplate.update(sql, customerId);
    }
}
