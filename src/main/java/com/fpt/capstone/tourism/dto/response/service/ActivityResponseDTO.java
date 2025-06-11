package com.fpt.capstone.tourism.dto.response.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ActivityResponseDTO {
    private Long serviceId;
    private String name;
    private double sellingPrice;
    private String imageUrl;
    private int quantity;
    private LocalDateTime checkInDate;
    private LocalDateTime checkOutDate;
}
