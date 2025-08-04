package com.sunbeam.service;

import java.util.List;
import java.util.Optional;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Hex;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import com.sunbeam.dao.BookingDAO;
import com.sunbeam.dao.PaymentDao;
import com.sunbeam.dto.PaymentRequestDTO;
import com.sunbeam.dto.PaymentResponseDTO;
import com.sunbeam.dto.RazorpayVerificationRequest;
import com.sunbeam.dto.TurfPaymentDTO;
import com.sunbeam.dto.VerifyAndSavePaymentDTO;
import com.sunbeam.entities.Booking;
import com.sunbeam.entities.Payment;
import com.sunbeam.entities.PaymentMethods;



@Service
@Transactional
public class PaymentServiceImpl implements PaymentService {
	
	@Autowired
	private PaymentDao paymentDao;
	@Autowired
	private BookingDAO bookingDao;

    @Value("${razorpay.key}")
    private String razorpayKey;

    @Value("${razorpay.secret}")
    private String razorpaySecret;

    @Override
    public PaymentResponseDTO createRazorpayOrder(PaymentRequestDTO request) {
    	try {
            RazorpayClient razorpayClient = new RazorpayClient(razorpayKey, razorpaySecret);

            JSONObject options = new JSONObject();
            options.put("amount", (int) (request.getAmount() * 100)); // amount in paise
            options.put("currency", request.getCurrency());
            options.put("receipt", "txn_" + System.currentTimeMillis());

            Order order = razorpayClient.orders.create(options);

            // Prepare response using setters
            PaymentResponseDTO response = new PaymentResponseDTO();
            response.setRazorpayOrderId(order.get("id"));
            response.setRazorpayKey(razorpayKey);
            response.setAmount(request.getAmount());
            response.setCurrency(request.getCurrency());

            return response;

        } catch (RazorpayException e) {
            throw new RuntimeException("Error creating Razorpay order: " + e.getMessage());
        }
    }

	@Override
	public boolean verifyPaymentSignature(RazorpayVerificationRequest request) {
		// TODO Auto-generated method stub
		try {
	        String payload = request.getRazorpayOrderId() + "|" + request.getRazorpayPaymentId();

	        Mac sha256Hmac = Mac.getInstance("HmacSHA256");
	        SecretKeySpec secretKey = new SecretKeySpec(razorpaySecret.getBytes(), "HmacSHA256");
	        sha256Hmac.init(secretKey);

	        byte[] hash = sha256Hmac.doFinal(payload.getBytes());
	        String generatedSignature = Hex.encodeHexString(hash);

	        return generatedSignature.equals(request.getRazorpaySignature());
	    } catch (Exception e) {
	        e.printStackTrace();
	        return false;
	    }
	}
	
	public String generateTestSignature(String orderId, String paymentId) {
	    try {
	        String payload = orderId + "|" + paymentId;

	        Mac sha256Hmac = Mac.getInstance("HmacSHA256");
	        SecretKeySpec secretKey = new SecretKeySpec(razorpaySecret.getBytes(), "HmacSHA256");
	        sha256Hmac.init(secretKey);

	        byte[] hash = sha256Hmac.doFinal(payload.getBytes());
	        return Hex.encodeHexString(hash);
	    } catch (Exception e) {
	        e.printStackTrace();
	        return null;
	    }
	}
	
	public void savePayment(RazorpayVerificationRequest request,
            float amount,
            PaymentMethods method,
            boolean status,
            Booking booking,
            Long userId,
            Long turfId) {
			Payment payment = new Payment();
			payment.setAmountPaid(amount);
			payment.setPaymentMethod(method);
			payment.setPaymentStatus(status);
			payment.setRazorpayPaymentId(request.getRazorpayPaymentId());
			payment.setRazorpayOrderId(request.getRazorpayOrderId());
			payment.setRazorpaySignature(request.getRazorpaySignature());
			payment.setBooking(booking);
			payment.setUserId(userId);
			payment.setTurfId(turfId);
			
			paymentDao.save(payment);
	}

	@Override
	public boolean verifyAndSave(VerifyAndSavePaymentDTO dto) {
		// TODO Auto-generated method stub
		// Step 1: Verify signature
	    RazorpayVerificationRequest request = new RazorpayVerificationRequest(
	        dto.getRazorpayPaymentId(),
	        dto.getRazorpayOrderId(),
	        dto.getRazorpaySignature()
	    );

	    if (!verifyPaymentSignature(request)) return false;

	    // Step 2: Fetch booking
	    Optional<Booking> optionalBooking = bookingDao.findById(dto.getBookingId());
	    if (!optionalBooking.isPresent()) return false;
	    
	    Booking booking = optionalBooking.get();
	    
	    // Step 3: Mark slot as booked
	    if (booking.getSlot() != null) {
	        booking.getSlot().setBooked(true);
	    }

	    // Step 3: Save payment
	    savePayment(
	        request,
	        dto.getAmountPaid(),
	        dto.getPaymentMethod(),
	        dto.isPaymentStatus(),
	        optionalBooking.get(),
	        dto.getUserId(),
	        dto.getTurfId()
	    );

	    return true;
	}

	@Override
	public List<TurfPaymentDTO> getPaymentsByTurfId(Long turfId) {
		// TODO Auto-generated method stub
		
		List<Payment> payments = paymentDao.findByTurfId(turfId);

	    return payments.stream().map(p -> new TurfPaymentDTO(
	            p.getId(),
	            p.getAmountPaid(),
	            p.getPaymentMethod(),
	            p.isPaymentStatus(),
	            p.getRazorpayPaymentId(),
	            p.getRazorpayOrderId(),
	            p.getBooking().getId(),
	            p.getUserId()
	    )).toList();
	}
}
