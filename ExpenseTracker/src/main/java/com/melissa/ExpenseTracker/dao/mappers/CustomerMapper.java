package com.melissa.ExpenseTracker.dao.mappers;

import java.sql.ResultSet;
        import java.sql.SQLException;

import com.melissa.ExpenseTracker.dto.Customer;
import org.springframework.jdbc.core.RowMapper;

public class CustomerMapper implements RowMapper<Customer> {
    @Override
    public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
        Customer customer = new Customer();
        customer.setCustomerId(rs.getInt("customerId"));
        customer.setFirstName(rs.getString("firstName"));
        customer.setLastName(rs.getString("lastName"));
        customer.setEmail(rs.getString("email"));
        return customer;
    }
}
