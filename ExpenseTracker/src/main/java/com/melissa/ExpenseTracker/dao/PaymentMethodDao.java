package com.melissa.ExpenseTracker.dao;

        import com.melissa.ExpenseTracker.dto.PaymentMethod;

        import java.util.List;

public interface PaymentMethodDao {
    void addPaymentMethod(PaymentMethod paymentMethod);
    PaymentMethod getPaymentMethodById(int paymentMethodId);
    List<PaymentMethod> getAllPaymentMethods();
    void updatePaymentMethod(PaymentMethod paymentMethod);
    void deletePaymentMethod(int paymentMethodId);
}
