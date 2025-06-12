package com.fpt.capstone.tourism.repository;

import com.fpt.capstone.tourism.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByPlanId(Long planId);
}
