package com.example.cardealer.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Payment {
    private long paymentId;
    private String paymentType;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "Payment_Id")
    public long getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(long paymentId) {
        this.paymentId = paymentId;
    }

    @Basic
    @Column(name = "Payment_Type")
    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Payment payment = (Payment) o;
        return paymentId == payment.paymentId && Objects.equals(paymentType, payment.paymentType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(paymentId, paymentType);
    }
}
