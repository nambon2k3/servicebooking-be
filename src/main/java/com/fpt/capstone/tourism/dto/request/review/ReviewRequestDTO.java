package com.fpt.capstone.tourism.dto.request.review;


import lombok.Data;

@Data
public class ReviewRequestDTO {
    private String content;
    private Long userId;
}
