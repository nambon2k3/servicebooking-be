package com.fpt.capstone.tourism.dto.request.cart;

import lombok.Data;

@Data
public class AddToCartRequestDTO {
    private Long serviceId;
    private int quantity;
    private Long userId;
}
