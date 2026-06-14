package com.zen.mapper;

import com.zen.dto.BookingDTO;
import com.zen.model.Booking;

public class BookingMapper {

    public static BookingDTO toDto(Booking booking){
        BookingDTO bookingDTO=new BookingDTO();
        bookingDTO.setId(booking.getId());
        bookingDTO.setCustomerId(bookingDTO.getCustomerId());
        bookingDTO.setStatus(booking.getStatus());
        bookingDTO.setStartTime(booking.getStartTime());
        bookingDTO.setEndTime(booking.getEndTime());
        bookingDTO.setSalonId(booking.getSalonId());
        bookingDTO.setServicesIds(booking.getServicesIds());

        return bookingDTO;
    }

}
