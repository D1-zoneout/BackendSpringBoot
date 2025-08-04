package com.sunbeam.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sunbeam.dto.PaymentRequestDTO;
import com.sunbeam.dto.PaymentResponseDTO;
import com.sunbeam.dto.RazorpayVerificationRequest;
import com.sunbeam.dto.TurfPaymentDTO;
import com.sunbeam.dto.VerifyAndSavePaymentDTO;
import com.sunbeam.service.PaymentService;

@RestController
@RequestMapping("/payments")
@CrossOrigin
public class PaymentController {
	
	@Autowired
    private PaymentService paymentService;
	
	@PostMapping("/create-order")
	public ResponseEntity<?> createOrder(@RequestBody PaymentRequestDTO request) {
	    PaymentResponseDTO response = paymentService.createRazorpayOrder(request);

	    if (response == null)
	        return ResponseEntity.status(500).body("Unable to create Razorpay order");

	    return ResponseEntity.ok(response);
	}
	
	@PostMapping("/verify")
    public ResponseEntity<String> verifyPayment(@RequestBody RazorpayVerificationRequest request) {
        boolean isValid = paymentService.verifyPaymentSignature(request);

        if (isValid) {
            // TODO: You can save payment info here in DB
            return ResponseEntity.ok("✅ Payment verified successfully");
        } else {
            return ResponseEntity.badRequest().body("❌ Invalid payment signature");
        }
    }
	
	@PostMapping("/verify-and-save")
	public ResponseEntity<String> verifyAndSave(@RequestBody VerifyAndSavePaymentDTO dto) {
	    boolean saved = paymentService.verifyAndSave(dto);
	    if (saved)
	        return ResponseEntity.ok("✅ Payment verified and saved");
	    else
	        return ResponseEntity.badRequest().body("❌ Invalid signature or booking not found");
	}
	
	@GetMapping("/turf/{turfId}")
	public ResponseEntity<?> getPaymentsByTurfId(@PathVariable Long turfId) {
	    List<TurfPaymentDTO> payments = paymentService.getPaymentsByTurfId(turfId);
	    return ResponseEntity.ok(payments);
	}
	
}
