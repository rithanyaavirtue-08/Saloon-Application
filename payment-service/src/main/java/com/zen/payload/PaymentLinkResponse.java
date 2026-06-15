package com.zen.payload;

import lombok.Data;

@Data
public class PaymentLinkResponse {

    private String payment_link_url;
    public String getPayment_link_url;
}
