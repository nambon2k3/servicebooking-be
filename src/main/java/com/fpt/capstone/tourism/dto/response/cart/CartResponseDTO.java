package com.fpt.capstone.tourism.dto.response.cart;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CartResponseDTO {
    List<CartItemRoomResponseDTO> hotelItems;
    List<CartItemMealResponseDTO> mealItems;
    List<CartItemActivityResponseDTO> activityItems;
}
