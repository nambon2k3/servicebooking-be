package com.fpt.capstone.tourism.dto.response.cart;

import com.fpt.capstone.tourism.dto.response.PublicServiceDTO;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CartItemRoomResponseDTO {
    private Long id;
    private PublicServiceDTO service;
    private int quantity;
    private LocalDateTime checkInDate;
    private LocalDateTime checkOutDate;


    public CartItemRoomResponseDTO(Long cartItemId,
                                   Long serviceId,
                                   String name,
                                   double sellingPrice,
                                   String imageUrl,
                                   Long roomId,
                                   Integer capacity,
                                   Integer availableQuantity,
                                   String facilities,
                                   int quantity,
                                   LocalDateTime checkInDate,
                                   LocalDateTime checkOutDate
                                   ) {
        this.id = cartItemId;
        this.service = PublicServiceDTO.builder()
                .serviceId(serviceId)
                .name(name)
                .sellingPrice(sellingPrice)
                .imageUrl(imageUrl)
                .roomId(roomId)
                .capacity(capacity)
                .availableQuantity(availableQuantity)
                .facilities(facilities)
                .build();
        this.quantity = quantity;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
    }

}
