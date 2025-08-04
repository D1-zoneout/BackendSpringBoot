package com.sunbeam.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Setter
@Getter 
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "payment")
public class Payment extends BaseEntity {
	@Column(name = "amount_paid")
    private float amountPaid;
	@Column(name = "payment_status")
    private boolean paymentStatus;
	private PaymentMethods paymentMethods; 
    private String razorpayPaymentId;
	@OneToOne
	@JoinColumn(name = "booking_id")
	private Booking booking;
	@Column(name = "user_id")
	private Long userId;
	@Column(name = "turf_id")
    private Long turfId;
	
}
