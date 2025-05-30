package com.fpt.capstone.tourism.dto.common;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StaffDTO {
    private Long id;
    private String fullName;
    private String email;
    private String phone;
    private String avatarImage;
}
