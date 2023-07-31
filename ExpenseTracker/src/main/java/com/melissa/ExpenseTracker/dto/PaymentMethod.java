package com.melissa.ExpenseTracker.dto;

public class PaymentMethod {
    private int paymentMethodId;
    private String name;

    // Constructors
    public PaymentMethod() {
    }

    public PaymentMethod(int paymentMethodId, String name) {
        this.paymentMethodId = paymentMethodId;
        this.name = name;
    }

    // Getters and Setters
    public int getPaymentMethodId() {
        return paymentMethodId;
    }

    public void setPaymentMethodId(int paymentMethodId) {
        this.paymentMethodId = paymentMethodId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // toString method (optional)
    @Override
    public String toString() {
        return "PaymentMethod [paymentMethodId=" + paymentMethodId + ", name=" + name + "]";
    }
}
