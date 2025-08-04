package com.sunbeam.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RazorpayVerificationRequest {
    private String razorpayPaymentId;
    private String razorpayOrderId;
    private String razorpaySignature;
}
