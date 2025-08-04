package com.sunbeam.test;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Hex;

public class SignatureTestRunner {

    public static void main(String[] args) {
        // 🔁 Replace with actual test values
        String razorpaySecret = "zrsIFpFdRkStmL61lnMz3sRd";       // <- from Razorpay Dashboard (test mode)
        String orderId = "order_R0JeoAbLPsKhee";               // <- from your create-order response
        String paymentId = "pay_TEST123456";                   // <- any fake value to simulate

        String payload = orderId + "|" + paymentId;

        try {
            Mac sha256Hmac = Mac.getInstance("HmacSHA256");
            SecretKeySpec secretKey = new SecretKeySpec(razorpaySecret.getBytes(), "HmacSHA256");
            sha256Hmac.init(secretKey);

            byte[] hash = sha256Hmac.doFinal(payload.getBytes());
            String signature = Hex.encodeHexString(hash);

            System.out.println("✅ Generated Signature: " + signature);
        } catch (Exception e) {
            System.err.println("❌ Error generating signature");
            e.printStackTrace();
        }
    }
}
