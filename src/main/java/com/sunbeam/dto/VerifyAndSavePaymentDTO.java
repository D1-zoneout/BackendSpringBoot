package com.sunbeam.dto;

import com.sunbeam.entities.PaymentMethods;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VerifyAndSavePaymentDTO {
    private String razorpayPaymentId;
    private String razorpayOrderId;
    private String razorpaySignature;

    private float amountPaid;
    private PaymentMethods paymentMethod;
    private boolean paymentStatus;

    private Long bookingId;
    private Long userId;
    private Long turfId;
}