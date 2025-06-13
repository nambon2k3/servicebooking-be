package com.fpt.capstone.tourism.dto.response.provider;

import com.fpt.capstone.tourism.dto.response.PublicServiceDTO;
import com.fpt.capstone.tourism.dto.response.PublicServiceProviderDTO;
import com.fpt.capstone.tourism.dto.response.service.MealResponseDTO;
import lombok.Builder;
import lombok.Data;

import java.util.List;
@Data
@Builder
public class PublicRestaurantDetailDTO {
    PublicServiceProviderDTO serviceProvider;
    List<MealResponseDTO> meals;
    List<PublicServiceProviderDTO> otherRestaurants;
}
