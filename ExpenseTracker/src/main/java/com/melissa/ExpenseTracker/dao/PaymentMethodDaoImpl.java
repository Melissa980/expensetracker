package com.melissa.ExpenseTracker.dao;

import com.melissa.ExpenseTracker.dao.mappers.PaymentMethodMapper;
import com.melissa.ExpenseTracker.dto.PaymentMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PaymentMethodDaoImpl implements PaymentMethodDao {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PaymentMethodDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void addPaymentMethod(PaymentMethod paymentMethod) {
        String sql = "INSERT INTO PaymentMethod (name) VALUES (?)";
        jdbcTemplate.update(sql, paymentMethod.getName());
    }

    @Override
    public PaymentMethod getPaymentMethodById(int paymentMethodId) {
        String sql = "SELECT * FROM PaymentMethod WHERE paymentMethodId = ?";
        return jdbcTemplate.queryForObject(sql, new PaymentMethodMapper(), paymentMethodId);
    }

    @Override
    public List<PaymentMethod> getAllPaymentMethods() {
        String sql = "SELECT * FROM PaymentMethod";
        return jdbcTemplate.query(sql, new PaymentMethodMapper());
    }

    @Override
    public void updatePaymentMethod(PaymentMethod paymentMethod) {
        String sql = "UPDATE PaymentMethod SET name = ? WHERE paymentMethodId = ?";
        jdbcTemplate.update(sql, paymentMethod.getName(), paymentMethod.getPaymentMethodId());
    }

    @Override
    public void deletePaymentMethod(int paymentMethodId) {
        String sql = "DELETE FROM PaymentMethod WHERE paymentMethodId = ?";
        jdbcTemplate.update(sql, paymentMethodId);
    }
}
