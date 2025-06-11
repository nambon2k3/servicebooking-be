package com.fpt.capstone.tourism.dto.response.cart;

import com.fpt.capstone.tourism.dto.response.PublicServiceDTO;
import com.fpt.capstone.tourism.dto.response.service.ActivityResponseDTO;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CartItemActivityResponseDTO {
    private Long id;
    private ActivityResponseDTO service;
    private int quantity;
    private LocalDateTime checkInDate;
    private LocalDateTime checkOutDate;



    public CartItemActivityResponseDTO(Long cartItemId, Long serviceId, String name,
                                       double sellingPrice, String imageUrl,
                                       int quantity, LocalDateTime checkInDate,
                                       LocalDateTime checkOutDate) {
        this.id = cartItemId;
        this.service = new ActivityResponseDTO(serviceId, name, sellingPrice, imageUrl, 0, null, null);
        this.quantity = quantity;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
    }

}
