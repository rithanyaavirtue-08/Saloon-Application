package com.zen.impl;

import com.razorpay.Payment;
import com.razorpay.PaymentLink;
import com.zen.domain.PaymentMethod;
import com.zen.model.PaymentOrder;
import com.zen.payload.PaymentLinkResponse;
import com.zen.payload.dto.BookingDTO;
import com.zen.payload.dto.UserDTO;
import com.zen.repository.PaymentOrderRepository;
import com.zen.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.NotReadablePropertyException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

      private final PaymentOrderRepository paymentOrderRepository;

      @Value("${stripe.api.key}")
      private String stripeSecretKey;

//values injecting
      @Value("${razorPay.api.key}")
      private  String razorPayApiKey;

    @Value("${razorPay.api.secret}")
      private String razorpayApiKey;

    @Override
    public PaymentLinkResponse createOrder(UserDTO user,
                                           BookingDTO booking,
                                           PaymentMethod paymentMethod) {

        PaymentLinkResponse paymen

         Long amount =booking.getTotalPrice();
         PaymentOrder order= new PaymentOrder();
          order.setAmount(amount);
          order.setPaymentMethod(paymentMethod);
          order.setBookingId(booking.getId());
          order.setSalonId(booking.getId());
          PaymentOrder savedOrder= paymentOrderRepository.save(order);

          if(paymentMethod.equals(PaymentMethod.RAZORPAY)){
               PaymentLink payment=createRazorpayPaymentLink(user,
                       savedOrder.getAmount(),
                       savedOrder.getId());

              String paymentUrl=payment.get("short_url");
          }


        return null;
    }

    @Override
    public PaymentOrder getPaymentsOrderById(Long id) {
        return null;
    }

    @Override
    public PaymentOrder getPaymentOrderByPaymentId(String paymentId) {
        return null;
    }

    @Override
    public PaymentLink createRazorpayPaymentLink(UserDTO user, Long amount, Long orderId) {
        return null;
    }

    @Override
    public String createStripePaymentLink(UserDTO user, Long amount, Long orderId) {
        return "";
    }
}
