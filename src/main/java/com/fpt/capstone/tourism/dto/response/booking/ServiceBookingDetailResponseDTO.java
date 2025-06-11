package com.fpt.capstone.tourism.dto.response.booking;

import com.fpt.capstone.tourism.dto.response.PublicServiceDTO;
import com.fpt.capstone.tourism.dto.response.service.ActivityResponseDTO;
import com.fpt.capstone.tourism.dto.response.service.MealResponseDTO;
import com.fpt.capstone.tourism.model.enums.BookingStatus;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ServiceBookingDetailResponseDTO {
    List<PublicServiceDTO> rooms;
    List<ActivityResponseDTO> activities;
    List<MealResponseDTO> meals;
    private String paymentUrl;
    private BookingStatus bookingStatus;
}
