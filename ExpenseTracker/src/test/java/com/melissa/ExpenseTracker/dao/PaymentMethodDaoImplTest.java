/*package com.melissa.ExpenseTracker.dao;

import com.melissa.ExpenseTracker.dao.mappers.PaymentMethodMapper;
import com.melissa.ExpenseTracker.dto.PaymentMethod;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@JdbcTest
@ActiveProfiles("test")
public class PaymentMethodDaoImplTest {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private PaymentMethodDao paymentMethodDao;

    @BeforeEach
    public void setup() {
        paymentMethodDao = new PaymentMethodDaoImpl(jdbcTemplate);
    }

    @Test
    public void testAddPaymentMethod() {
        PaymentMethod paymentMethod = new PaymentMethod();
        paymentMethod.setName("Credit Card");

        paymentMethodDao.addPaymentMethod(paymentMethod);

        assertNotNull(paymentMethod.getPaymentMethodId());
    }

    @Test
    public void testGetPaymentMethodById() {
        int paymentMethodId = 1; // Replace with an existing payment method ID

        PaymentMethod paymentMethod = paymentMethodDao.getPaymentMethodById(paymentMethodId);

        assertNotNull(paymentMethod);
        assertEquals(paymentMethodId, paymentMethod.getPaymentMethodId());
    }

    @Test
    public void testGetAllPaymentMethods() {
        List<PaymentMethod> paymentMethods = paymentMethodDao.getAllPaymentMethods();

        // Replace the following assertions with the actual expected number of payment methods in the database
        assertNotNull(paymentMethods);
        assertEquals(3, paymentMethods.size());
    }

    @Test
    public void testUpdatePaymentMethod() {
        int paymentMethodId = 1; // Replace with an existing payment method ID

        PaymentMethod paymentMethod = paymentMethodDao.getPaymentMethodById(paymentMethodId);
        assertNotNull(paymentMethod);

        String newName = "Debit Card";
        paymentMethod.setName(newName);

        paymentMethodDao.updatePaymentMethod(paymentMethod);

        PaymentMethod updatedPaymentMethod = paymentMethodDao.getPaymentMethodById(paymentMethodId);
        assertEquals(newName, updatedPaymentMethod.getName());
    }

    @Test
    public void testDeletePaymentMethod() {
        int paymentMethodId = 1; // Replace with an existing payment method ID

        paymentMethodDao.deletePaymentMethod(paymentMethodId);

        // Verify that the payment method is no longer in the database
        PaymentMethod deletedPaymentMethod = paymentMethodDao.getPaymentMethodById(paymentMethodId);
        assertNull(deletedPaymentMethod);
    }
}
*/