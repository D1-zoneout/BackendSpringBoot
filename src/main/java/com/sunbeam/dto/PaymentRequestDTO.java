package com.sunbeam.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PaymentRequestDTO {
    private float amount;
    private Long userId;
    private Long turfId;
    private Long bookingId;
    private String currency = "INR"; // Optional: Razorpay default is INR
}
