package com.zen.controller;


import com.zen.domain.BookingStatus;
import com.zen.dto.*;
import com.zen.mapper.BookingMapper;
import com.zen.model.Booking;
import com.zen.model.SalonReport;
import com.zen.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/bookings")
public class BookingController {

    public final BookingService bookingService;


    @PostMapping
    public ResponseEntity<Booking> createBooking(
            @RequestParam Long salonId,
            @RequestParam BookingStatus.PaymentMethod paymentMethod,
            @RequestBody BookingRequest bookingRequest
            ) throws Exception {
        UserDTO user=new UserDTO();
        user.setId(1L);

        SalonDTO salon=new SalonDTO();
        salon.setId(salonId);
        salon.setOpenTime(LocalTime.of(9,0));
        salon.setCloseTime(LocalTime.of(21,0));

        Set<ServiceDTO> serviceDTOSet=new HashSet<>();
        ServiceDTO serviceDTO=new ServiceDTO();
        serviceDTO.setId(1L);
        serviceDTO.setPrice(399);
        serviceDTO.setDuration(45);
        serviceDTO.setName("Hair Cut for men");
        serviceDTOSet.add(serviceDTO);

        Booking booking=bookingService.createBooking(bookingRequest
                ,user,
                salon,
                serviceDTOSet);
        return ResponseEntity.ok(booking);

    }

    @GetMapping("/customer"
    )
    public ResponseEntity<Set<BookingDTO>> getBookingsByCustomer(

    ){
        UserDTO user=new UserDTO();
        user.setId(1L);

        List<Booking> bookings=bookingService.getBookingByCustomer(1L);

        return ResponseEntity.ok(getBookingDTos(bookings));
    }

    private Set<BookingDTO> getBookingDTos(List<Booking> bookings){
        return bookings.stream()
                .map(booking -> {
                    return BookingMapper.toDto(booking);
                }).collect(Collectors.toSet());
    }

    @GetMapping("/salon")
    public ResponseEntity<Set<BookingDTO>> getBookingsBySalon(

    ){
        UserDTO user=new UserDTO();
        user.setId(1L);

        List<Booking> bookings=bookingService.getBookingBySalon(1L);

        return ResponseEntity.ok(getBookingDTos(bookings));
    }
    @GetMapping("/{BookingId}")
    public ResponseEntity<BookingDTO> getBookingById(
      @PathVariable Long BookingId
    ) throws Exception {
        Booking bookings=bookingService.getBookingById(BookingId);

        return ResponseEntity.ok(BookingMapper.toDto(bookings));
    }

    @PutMapping("/{bookingId}/status")
    public ResponseEntity<BookingDTO> updateBookingStatus(
            @PathVariable Long bookingId,
            @RequestParam BookingStatus status
            ) throws Exception {

        Booking bookings=bookingService.updateBooking(bookingId,status);

        return ResponseEntity.ok(BookingMapper.toDto(bookings));
    }
    @GetMapping("/slots/saslon/{salonId}/date/{date}")
    public ResponseEntity<List<BookingSlotDTO>> getBookedSlot(
            @PathVariable Long salonId,
            @RequestParam(required = false) LocalDate date
            ) throws Exception {
        List<Booking> bookings=bookingService.getBookingsByDate(date,salonId);

        List<BookingSlotDTO> slotsDTOs=bookings.stream()
                .map(booking->{
                    BookingSlotDTO slotDTO = new BookingSlotDTO();
                    slotDTO.setStartTime(booking.getStartTime());
                    slotDTO.setEndTime(booking.getEndTime());
                    return slotDTO;
                }).collect(Collectors.toList());
        return ResponseEntity.ok(slotsDTOs);

    }
    @GetMapping("/report")
    public ResponseEntity<SalonReport> getSalonReport(
    ) throws Exception {
      SalonReport report =bookingService.getSalonReport(1L);
        return ResponseEntity.ok(report);

    }

}
