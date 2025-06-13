package com.fpt.capstone.tourism.dto.common;

import com.fpt.capstone.tourism.model.User;
import com.fpt.capstone.tourism.model.enums.BookingStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ServiceBookingDTO {
    private Long id;
    private String bookingCode;
    private Long userId; // Nếu đã có entity User
    private String userName;
    private double totalPrice;
    private BookingStatus status; // PENDING, CONFIRMED, CANCELED
    private LocalDateTime createdAt;
}
