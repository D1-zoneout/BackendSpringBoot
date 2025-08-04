package com.sunbeam.service;

import java.util.List;

import com.sunbeam.dto.PaymentRequestDTO;
import com.sunbeam.dto.PaymentResponseDTO;
import com.sunbeam.dto.RazorpayVerificationRequest;
import com.sunbeam.dto.TurfPaymentDTO;
import com.sunbeam.dto.VerifyAndSavePaymentDTO;

public interface PaymentService {

	PaymentResponseDTO createRazorpayOrder(PaymentRequestDTO request);
	
	boolean verifyPaymentSignature(RazorpayVerificationRequest request);

	boolean verifyAndSave(VerifyAndSavePaymentDTO dto);

	List<TurfPaymentDTO> getPaymentsByTurfId(Long turfId);



}
