package com.zen.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BookingSlotDTO {
    private LocalDateTime  StartTime;
    private LocalDateTime EndTime;
}
