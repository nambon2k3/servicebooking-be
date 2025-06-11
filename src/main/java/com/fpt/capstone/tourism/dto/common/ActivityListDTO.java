package com.fpt.capstone.tourism.dto.common;

import lombok.*;

import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ActivityListDTO {
    private Long id;
    private Double sellingPrice;
    private String imageUrl;
    private String activityName;
    private String categoryName;
    private String address;
    private String providerName;
    private String providerEmail;
    private String providerPhone;
    private String providerWebsite;
    private String locationName;
}