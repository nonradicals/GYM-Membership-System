package com.gym.model;

import java.time.LocalDate;

public class Payment {
    private final String paymentId;
    private final double amount;
    private final LocalDate date;
    private PaymentStatus status;

    public Payment(String paymentId, double amount, LocalDate date) {
        this.paymentId = paymentId;
        this.amount = amount;
        this.date = date;
        this.status = PaymentStatus.PENDING;
    }

    public boolean process() {
        this.status = PaymentStatus.COMPLETED;
        return true;
    }

    public String getPaymentId() { return paymentId; }
    public double getAmount() { return amount; }
    public LocalDate getDate() { return date; }
    public PaymentStatus getStatus() { return status; }
}
