package com.fpt.capstone.tourism.dto.request;

import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TourDayAllRequestDTO {
    private String title;
    private String content;
    private String mealPlan;
    private Long locationId;
    private List<TourDayServiceRequestDTO> tourDayServices;
}
