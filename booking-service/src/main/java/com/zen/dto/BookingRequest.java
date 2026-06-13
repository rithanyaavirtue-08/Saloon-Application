package com.zen.dto;

import lombok.Data;
import org.apache.commons.logging.LogSource;

import java.time.LocalDateTime;
import java.util.Set;

@Data
public class BookingRequest {

    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Set<Long> serviceTime;



}
