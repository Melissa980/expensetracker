package com.melissa.ExpenseTracker.dao.mappers;

import com.melissa.ExpenseTracker.dto.PaymentMethod;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PaymentMethodMapper implements RowMapper<PaymentMethod> {
    @Override
    public PaymentMethod mapRow(ResultSet rs, int rowNum) throws SQLException {
        PaymentMethod paymentMethod = new PaymentMethod();
        paymentMethod.setPaymentMethodId(rs.getInt("paymentMethodId"));
        paymentMethod.setName(rs.getString("name"));
        return paymentMethod;
    }
}
