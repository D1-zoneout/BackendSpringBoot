package com.sunbeam.dto;

import com.sunbeam.entities.PaymentMethods;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TurfPaymentDTO {

	 private Long paymentId;
	    private float amountPaid;
	    private PaymentMethods paymentMethod;
	    private boolean paymentStatus;
	    private String razorpayPaymentId;
	    private String razorpayOrderId;
	    private Long bookingId;
	    private Long userId;
}
