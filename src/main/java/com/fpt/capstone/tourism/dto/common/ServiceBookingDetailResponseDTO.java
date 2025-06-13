package com.fpt.capstone.tourism.dto.common;

import com.fpt.capstone.tourism.dto.response.cart.CartItemActivityResponseDTO;
import com.fpt.capstone.tourism.dto.response.cart.CartItemMealResponseDTO;
import com.fpt.capstone.tourism.dto.response.cart.CartItemRoomResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ServiceBookingDetailResponseDTO {
    private String bookingCode;
    List<RoomDetailResponseDTO> hotelItems;
    List<MealDetailResponseDTO> mealItems;
    List<ServiceBookingDetailDTO> activityItems;
}
