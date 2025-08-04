package com.sunbeam.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "payments")
public class Payment extends BaseEntity {

    @Column(name = "amount_paid", nullable = false)
    private float amountPaid;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_method", nullable = false, length=20)
    private PaymentMethods paymentMethod;

    @Column(name = "payment_status", nullable = false)
    private boolean paymentStatus;

    @Column(name = "razorpay_payment_id")
    private String razorpayPaymentId;

    @Column(name = "razorpay_order_id")
    private String razorpayOrderId;

    @Column(name = "razorpay_signature")
    private String razorpaySignature;

    @OneToOne
    @JoinColumn(name = "booking_id", nullable = false)
    private Booking booking;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "turf_id", nullable = false)
    private Long turfId;
}
