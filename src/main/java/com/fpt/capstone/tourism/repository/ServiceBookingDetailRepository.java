package com.fpt.capstone.tourism.repository;

import com.fpt.capstone.tourism.model.ServiceBooking;
import com.fpt.capstone.tourism.model.ServiceBookingDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ServiceBookingDetailRepository extends JpaRepository<ServiceBookingDetail, Long> {
    List<ServiceBookingDetail> findServiceBookingDetailsByBookingService(ServiceBooking serviceBooking);
}
