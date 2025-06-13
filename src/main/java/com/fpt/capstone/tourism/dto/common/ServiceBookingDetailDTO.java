package com.fpt.capstone.tourism.dto.common;

import com.fpt.capstone.tourism.dto.response.cart.CartItemActivityResponseDTO;
import com.fpt.capstone.tourism.dto.response.cart.CartItemMealResponseDTO;
import com.fpt.capstone.tourism.dto.response.cart.CartItemRoomResponseDTO;
import com.fpt.capstone.tourism.model.Service;
import com.fpt.capstone.tourism.model.ServiceBooking;
import com.fpt.capstone.tourism.model.enums.MealType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ServiceBookingDetailDTO {
    private Long serviceId;
    private String name;
    private double sellingPrice;
    private double nettPrice;
    private String imageUrl;
    private int quantity;
    private LocalDateTime checkInDate;
    private LocalDateTime checkOutDate;
}
