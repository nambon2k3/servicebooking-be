package com.fpt.capstone.tourism.dto.common;

import com.fpt.capstone.tourism.dto.response.TourDetailSaleResponseDTO;
import com.fpt.capstone.tourism.model.*;
import com.fpt.capstone.tourism.model.enums.TourScheduleStatus;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;

@Data
@Builder
public class TourScheduleSettlementDTO {
    private Long id;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Boolean deleted;
    private TourScheduleStatus status;
    private TourSupportInfoDTO tour;
    private StaffDTO tourGuide;
    private StaffDTO operator;
    private String meetingLocation;
    private LocalTime departureTime;
}
