package com.fpt.capstone.tourism.dto.response.service;

import com.fpt.capstone.tourism.model.enums.MealType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MealResponseDTO {
    private Long serviceId;
    private String name;
    private double sellingPrice;
    private String imageUrl;
    private MealType type;
    private String mealDetail;
    private int quantity;
    private LocalDateTime checkInDate;
    private LocalDateTime checkOutDate;
}
