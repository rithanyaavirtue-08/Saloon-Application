package com.zen.impl;


import com.zen.domain.BookingStatus;
import com.zen.dto.BookingRequest;
import com.zen.dto.SalonDTO;
import com.zen.dto.ServiceDTO;
import com.zen.dto.UserDTO;
import com.zen.model.Booking;
import com.zen.model.SalonReport;
import com.zen.repository.BookingRepository;
import com.zen.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.PublicKey;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    @Override
    public Booking createBooking(BookingRequest booking,
                                 UserDTO user, SalonDTO salon, Set<ServiceDTO> serviceDTOSst) {

        int totalDuration= serviceDTOSst.stream()
                .mapToInt(ServiceDTO::getDuration)
                .sum();
        LocalDateTime bookingStartTime=booking.getStartTime();
        LocalDateTime bookingEndTime=bookingStartTime.plusMinutes(totalDuration);
        return null;
    }
    public Boolean isTimeSlotAvailable(SalonDTO salonDTO,LocalDateTime bookingStartTime,
                                       LocalDateTime bookingEndTime){

        if(bookingStartTime.isBefore(salonDTO.getOpenTime())){

        }throw new Exception("Booking time must bew within Salon'")
        return true;
    }

    @Override
    public List<Booking> getBookingByCustomer(Long customerId) {
        return List.of();
    }

    @Override
    public List<Booking> getBookingBySalon(Long salonId) {
        return List.of();
    }

    @Override
    public Booking getBookingById(Long id) {
        return null;
    }

    @Override
    public Booking updateBooking(Long BookingId, BookingStatus status) {
        return null;
    }

    @Override
    public List<Booking> getBookingsByDate(LocalDate date, Long salonId) {
        return List.of();
    }

    @Override
    public SalonReport getSalonReport(Long salonId) {
        return null;
    }
}
