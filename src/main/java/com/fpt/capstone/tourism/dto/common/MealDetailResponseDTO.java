package com.fpt.capstone.tourism.dto.common;

import com.fpt.capstone.tourism.model.enums.MealType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MealDetailResponseDTO extends ServiceBookingDetailDTO{
    private MealType type;
    private String mealDetail;

    public MealDetailResponseDTO(Long serviceId, String name, double sellingPrice, double nettPrice, String imageUrl, int quantity, LocalDateTime checkInDate, LocalDateTime checkOutDate, MealType type, String mealDetail) {
        super(serviceId, name, sellingPrice, nettPrice, imageUrl, quantity, checkInDate, checkOutDate);
        this.type = type;
        this.mealDetail = mealDetail;
    }
}
