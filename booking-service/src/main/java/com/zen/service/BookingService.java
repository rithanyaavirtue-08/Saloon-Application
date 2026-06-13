package com.zen.service;

import com.zen.domain.BookingStatus;
import com.zen.dto.BookingRequest;
import com.zen.dto.SalonDTO;
import com.zen.dto.ServiceDTO;
import com.zen.dto.UserDTO;
import com.zen.model.Booking;
import com.zen.model.SalonReport;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.function.LongFunction;

public interface BookingService {

    Booking createBooking(BookingRequest booking, UserDTO userDTO, SalonDTO salonDTO,
                          Set<ServiceDTO> serviceDTOSst);

    List<Booking> getBookingByCustomer(Long customerId);
    List<Booking> getBookingBySalon(Long salonId);
    Booking getBookingById(Long id);
    Booking updateBooking(Long BookingId, BookingStatus status);
    List<Booking> getBookingsByDate(LocalDate date, Long salonId);
    SalonReport getSalonReport(Long salonId);
}

