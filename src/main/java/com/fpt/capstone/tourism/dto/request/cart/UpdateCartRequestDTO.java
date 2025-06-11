package com.fpt.capstone.tourism.dto.request.cart;

import lombok.Builder;
import lombok.Data;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
public class UpdateCartRequestDTO {
    private Long cartItemId;
    private int quantity;
    private LocalDateTime checkInDate;
    private LocalDateTime checkOutDate;
}
