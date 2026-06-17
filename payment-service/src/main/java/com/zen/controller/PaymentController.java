package com.zen.controller;


import com.razorpay.RazorpayException;
import com.stripe.exception.StripeException;
import com.zen.domain.PaymentMethod;

import com.zen.model.PaymentOrder;
import com.zen.payload.PaymentLinkResponse;
import com.zen.payload.dto.BookingDTO;
import com.zen.payload.dto.UserDTO;
import com.zen.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.message.StringFormattedMessage;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentService paymentService;

    @PostMapping("/create")
    public ResponseEntity<PaymentLinkResponse> createPaymentLink(
            @RequestBody BookingDTO booking,
            @RequestParam PaymentMethod paymentMethod
    ) throws StripeException, RazorpayException {

        UserDTO user = new UserDTO();

        user.setFullName("Ashok");
        user.setEmail("ashok@gmail.com");
        user.setId(1L);

        PaymentLinkResponse res =
                paymentService.createOrder(user, booking, paymentMethod);

        return ResponseEntity.ok(res);
    }

    @GetMapping("/{paymentOrderId}")
    public ResponseEntity<PaymentOrder> getPaymentOrderById(
            @PathVariable Long paymentOrderId
    ) throws Exception {


       PaymentOrder res =
                paymentService.getPaymentsOrderById(paymentOrderId);

        return ResponseEntity.ok(res);
    }
    @PatchMapping("/proceed")
    public ResponseEntity<Boolean> proceedPayment(
            @RequestParam String paymentId,
            @RequestParam String paymentLinkId

            ) throws Exception {
       PaymentOrder paymentOrder=paymentService.getPaymentOrderByPaymentId(paymentId);

        Boolean res =
                paymentService.processedPayment(paymentOrder,paymentId,paymentLinkId);


        return ResponseEntity.ok(res);
    }



}
