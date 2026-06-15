package com.zen.service;

import com.zen.domain.PaymentMethod;
import com.zen.model.PaymentOrder;
import com.zen.payload.PaymentLinkResponse;
import com.zen.payload.dto.BookingDTO;
import com.zen.payload.dto.UserDTO;

public interface PaymentService {

   PaymentLinkResponse createOrder(UserDTO user,
                                   BookingDTO booking,
                                   PaymentMethod paymentMethod);

   PaymentOrder getPaymentsOrderById(Long id);

   PaymentOrder getPaymentOrderByPaymentId(String paymentId);

  PaymentLink

}
