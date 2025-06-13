package com.fpt.capstone.tourism.dto.response.review;

import com.fpt.capstone.tourism.dto.response.UserBasicDTO;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ReviewResponseDTO {
    private Long id;
    private String content;
    private UserBasicDTO user;
    private LocalDateTime createdAt;
}
