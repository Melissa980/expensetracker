/*package com.melissa.ExpenseTracker.dao;
import static org.junit.jupiter.api.Assertions.assertEquals;
        import static org.junit.jupiter.api.Assertions.assertNotNull;

        import java.util.List;

        import com.melissa.ExpenseTracker.dto.Customer;
        import org.junit.jupiter.api.BeforeEach;
        import org.junit.jupiter.api.Test;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.boot.test.context.SpringBootTest;
        import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootTest
public class CustomerDaoImplTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private CustomerDaoImpl customerDao;

    @BeforeEach
    public void setup() {
        customerDao = new CustomerDaoImpl(jdbcTemplate);
    }

    @Test
    public void testAddAndGetCustomer() {
        Customer customer = new Customer();
        customer.setFirstName("John");
        customer.setLastName("Doe");
        customer.setEmail("john.doe@example.com");

        // Add the customer
        customerDao.addCustomer(customer);

        // Get the customer by ID
        Customer retrievedCustomer = customerDao.getCustomerById(customer.getCustomerId());
        assertNotNull(retrievedCustomer);
        assertEquals("John", retrievedCustomer.getFirstName());
        assertEquals("Doe", retrievedCustomer.getLastName());
        assertEquals("john.doe@example.com", retrievedCustomer.getEmail());
    }

    @Test
    public void testGetAllCustomers() {
        // Fetch all customers from the database
        List<Customer> customers = customerDao.getAllCustomers();
        assertNotNull(customers);
        // Assuming there are at least two customers in the database
        // Add additional assertions based on your actual data
    }

    @Test
    public void testUpdateCustomer() {
        // Assuming there is an existing customer with ID 1 in the database
        Customer existingCustomer = customerDao.getCustomerById(1);
        assertNotNull(existingCustomer);

        // Update the customer
        existingCustomer.setFirstName("Jane");
        existingCustomer.setLastName("Smith");
        existingCustomer.setEmail("jane.smith@example.com");
        customerDao.updateCustomer(existingCustomer);

        // Retrieve the updated customer from the database
        Customer updatedCustomer = customerDao.getCustomerById(existingCustomer.getCustomerId());
        assertNotNull(updatedCustomer);
        assertEquals("Jane", updatedCustomer.getFirstName());
        assertEquals("Smith", updatedCustomer.getLastName());
        assertEquals("jane.smith@example.com", updatedCustomer.getEmail());
    }

    @Test
    public void testDeleteCustomer() {
        // Assuming there is an existing customer with ID 1 in the database
        Customer existingCustomer = customerDao.getCustomerById(1);
        assertNotNull(existingCustomer);

        // Delete the customer
        customerDao.deleteCustomer(existingCustomer.getCustomerId());

        // Attempt to retrieve the deleted customer from the database
        Customer deletedCustomer = customerDao.getCustomerById(existingCustomer.getCustomerId());
        // The deletedCustomer should be null, indicating that the customer is deleted
        assertEquals(null, deletedCustomer);
    }
}
*/