package com.fpt.capstone.tourism.mapper;


import com.fpt.capstone.tourism.dto.response.review.ReviewResponseDTO;
import com.fpt.capstone.tourism.model.Review;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ReviewMapper {
    @Mapping(source = "createdAt", target = "createdAt")
    ReviewResponseDTO toDto(Review review);
}
