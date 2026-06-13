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
import org.springframework.aop.ThrowsAdvice;
import org.springframework.stereotype.Service;

import java.security.PublicKey;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    @Override
    public Booking createBooking(BookingRequest booking,
                                 UserDTO user, SalonDTO salon, Set<ServiceDTO> serviceDTOSet) throws Exception {

        int totalDuration= serviceDTOSet.stream()
                .mapToInt(ServiceDTO::getDuration)
                .sum();
        LocalDateTime bookingStartTime=booking.getStartTime();
        LocalDateTime bookingEndTime=bookingStartTime.plusMinutes(totalDuration);
        Boolean isSlotAvailable=isTimeSlotAvailable(salon,bookingStartTime,bookingEndTime);

        int totalPrice =serviceDTOSet.stream()
                .mapToInt(ServiceDTO::getPrice).sum();


        Set<Long> idList= serviceDTOSet.stream().map(ServiceDTO::getId).collect(Collectors.toSet());

        Booking newBooking=new Booking();
        newBooking.setCustomerId(user.getId());
        newBooking.setSalonId(salon.getId());
        newBooking.setServicesIds(idList);
        newBooking.setStatus(BookingStatus.PENDING);
        newBooking.setStartTime(bookingStartTime);
        newBooking.setEndTime(bookingEndTime);
        newBooking.setTotalPrice(totalPrice);
        return bookingRepository.save(newBooking);

    }
    public Boolean isTimeSlotAvailable(SalonDTO salonDTO,LocalDateTime bookingStartTime,
                                       LocalDateTime bookingEndTime) throws Exception {


        List<Booking> existingBooking=getBookingBySalon(salonDTO.getId());
        LocalDateTime salonOpenTime=salonDTO.getOpenTime()
                .atDate(bookingStartTime.toLocalDate());//saon open time only ,athu oda date um time um solrathu
        LocalDateTime salonCloseTime=salonDTO.getCloseTime().atDate(bookingStartTime.toLocalDate());

        if(bookingStartTime.isBefore(salonOpenTime) || bookingEndTime.isAfter(salonCloseTime)){

            throw new Exception("Booking time must be within salon's working hours");

        }
        for(Booking existingBookings:existingBooking ){
            LocalDateTime existingBookingStartTime=existingBookings.getStartTime();
            LocalDateTime existingBookingEndTime=existingBookings.getEndTime();

            if(bookingStartTime.isBefore(existingBookingEndTime)&&
                    bookingEndTime.isAfter(existingBookingStartTime)){
                throw new Exception("Slot Not Available ,choose different time.");
            }
            if(bookingStartTime.isEqual(existingBookingStartTime)|| bookingEndTime.isEqual(existingBookingEndTime)){
                throw new Exception("Slot Not Available ,choose different time.");
            }


        }
        return true;
    }

    @Override
    public List<Booking> getBookingByCustomer(Long customerId) {
        return bookingRepository.findByCustomerId(customerId);
    }

    @Override
    public List<Booking> getBookingBySalon(Long salonId) {
        return bookingRepository.findBySalonId(salonId);
    }

    @Override
    public Booking getBookingById(Long id) throws Exception {
       Booking booking =bookingRepository.findById(id).orElse(null);
        if(booking==null){
            throw new Exception("Booking not found");
        }
        return booking;
    }

    @Override
    public Booking updateBooking(Long BookingId, BookingStatus status) throws Exception {
       Booking booking =getBookingById(BookingId);
       booking.setStatus(status);
       return bookingRepository.save(booking);
    }

    @Override
    public List<Booking> getBookingsByDate(LocalDate date, Long salonId) {
     List<Booking> allBooking=getBookingBySalon(salonId);
     if(date==null){
         return allBooking;
     }
       return allBooking.stream()
               .filter(booking->isSameData(booking.getStartTime(),date)||
                       isSameData(booking.getEndTime(),date)).collect(Collectors.toList());
    }

    private boolean isSameData(LocalDateTime dateTime,LocalDate date) {
        return  dateTime.toLocalDate().isEqual(date);
    }

    @Override
    public SalonReport getSalonReport(Long salonId) {
        List<Booking> bookings=getBookingBySalon(salonId);
      Integer totalEarning=bookings.stream().mapToInt(Booking::getTotalPrice).sum();

        Integer totalBooking=bookings.size();

        List<Booking> cancelledBooking=bookings.stream().filter(booking-> booking.getStatus()
                .equals(BookingStatus.CANCELLED)).collect(Collectors.toList());

        Double totalRefund=cancelledBooking.stream()
                .mapToDouble(Booking::getTotalPrice).sum();

        SalonReport report=new SalonReport();
        report.setSalonId(salonId);
        report.setCancelledBooking(cancelledBooking.size());
        report.setTotalBookings(totalEarning);
        report.setTotalRefund(totalRefund);
        report.setTotalBookings(totalBooking);
        return report;


    }
}
