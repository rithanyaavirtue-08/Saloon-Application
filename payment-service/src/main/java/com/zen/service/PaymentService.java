package com.zen.service;

import com.razorpay.PaymentLink;
import com.zen.domain.PaymentMethod;
import com.zen.model.PaymentOrder;
import com.zen.payload.PaymentLinkResponse;
import com.zen.payload.dto.BookingDTO;
import com.zen.payload.dto.UserDTO;
import org.apache.catalina.User;
import org.hibernate.dialect.function.SpannerTruncFunction;

public interface PaymentService {

   PaymentLinkResponse createOrder(UserDTO user,
                                   BookingDTO booking,
                                   PaymentMethod paymentMethod);

   PaymentOrder getPaymentsOrderById(Long id);

   PaymentOrder getPaymentOrderByPaymentId(String paymentId);

  PaymentLink createRazorpayPaymentLink(UserDTO user,
                                        Long amount,
                                        Long orderId
                                        );

  String createStripePaymentLink(UserDTO user,
                                 Long amount,
                                 Long orderId);

}
