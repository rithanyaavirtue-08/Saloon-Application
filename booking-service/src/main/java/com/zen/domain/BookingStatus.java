package com.zen.domain;

public enum BookingStatus {
    PENDING,
    CONFIRMED,
    CANCELLED;

    public enum PaymentMethod {
        RAZORPAY,
        CASH,
        CARD
    }
}
