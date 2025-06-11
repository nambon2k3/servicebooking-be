package com.fpt.capstone.tourism.repository;

import com.fpt.capstone.tourism.model.ServiceBooking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ServiceBookingRepository extends JpaRepository<ServiceBooking, Long> {


    Optional<ServiceBooking> findByBookingCode(String bookingCode);
}
