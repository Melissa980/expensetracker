package com.melissa.ExpenseTracker.service;

import com.melissa.ExpenseTracker.dao.PaymentMethodDao;
import com.melissa.ExpenseTracker.dto.PaymentMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentMethodServiceImpl implements PaymentMethodService {
    private final PaymentMethodDao paymentMethodDao;

    @Autowired
    public PaymentMethodServiceImpl(PaymentMethodDao paymentMethodDao) {
        this.paymentMethodDao = paymentMethodDao;
    }

    @Override
    public void addPaymentMethod(PaymentMethod paymentMethod) {
        paymentMethodDao.addPaymentMethod(paymentMethod);
    }

    @Override
    public PaymentMethod getPaymentMethodById(int paymentMethodId) {
        return paymentMethodDao.getPaymentMethodById(paymentMethodId);
    }

    @Override
    public List<PaymentMethod> getAllPaymentMethods() {
        return paymentMethodDao.getAllPaymentMethods();
    }

    @Override
    public void updatePaymentMethod(PaymentMethod paymentMethod) {
        paymentMethodDao.updatePaymentMethod(paymentMethod);
    }

    @Override
    public void deletePaymentMethod(int paymentMethodId) {
        paymentMethodDao.deletePaymentMethod(paymentMethodId);
    }
}
