package com.melissa.ExpenseTracker.service;

import com.melissa.ExpenseTracker.dto.PaymentMethod;

import java.util.List;

public interface PaymentMethodService {
    void addPaymentMethod(PaymentMethod paymentMethod);
    PaymentMethod getPaymentMethodById(int paymentMethodId);
    List<PaymentMethod> getAllPaymentMethods();
    void updatePaymentMethod(PaymentMethod paymentMethod);
    void deletePaymentMethod(int paymentMethodId);
}
