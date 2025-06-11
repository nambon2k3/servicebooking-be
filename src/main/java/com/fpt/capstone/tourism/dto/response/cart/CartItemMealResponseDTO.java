package com.fpt.capstone.tourism.dto.response.cart;

import com.fpt.capstone.tourism.dto.response.PublicServiceDTO;
import com.fpt.capstone.tourism.dto.response.service.MealResponseDTO;
import com.fpt.capstone.tourism.model.enums.MealType;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CartItemMealResponseDTO {
    private Long id;
    private MealResponseDTO service;
    private int quantity;
    private LocalDateTime checkInDate;
    private LocalDateTime checkOutDate;


    public CartItemMealResponseDTO(Long cartItemId, Long serviceId, String name,
                                   double sellingPrice, String imageUrl,
                                   MealType mealType, String mealDetail,
                                   int quantity, LocalDateTime checkInDate,
                                   LocalDateTime checkOutDate) {
        this.id = cartItemId;
        this.service = new MealResponseDTO(serviceId, name, sellingPrice, imageUrl, mealType, mealDetail, 0, null, null);
        this.quantity = quantity;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
    }
}
