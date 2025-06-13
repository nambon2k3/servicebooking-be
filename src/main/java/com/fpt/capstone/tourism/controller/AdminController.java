package com.fpt.capstone.tourism.controller;

import com.fpt.capstone.tourism.dto.common.BookingStatusUpdateDTO;
import com.fpt.capstone.tourism.dto.common.GeneralResponse;
import com.fpt.capstone.tourism.dto.common.ServiceBookingDTO;
import com.fpt.capstone.tourism.dto.common.TourBookingWithDetailDTO;
import com.fpt.capstone.tourism.dto.response.PagingDTO;
import com.fpt.capstone.tourism.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {
    private final BookingService bookingService;
    @GetMapping("/bookings/list")
    public ResponseEntity<GeneralResponse<PagingDTO<List<ServiceBookingDTO>>>> getBookings(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(required = false) String bookingCode,
            @RequestParam(required = false) String paymentStatus) {
        return ResponseEntity.ok(bookingService.getServiceBookings(page, size, bookingCode, paymentStatus));
    }

    @GetMapping("/bookings/detail/{serviceBookingId}")
    public ResponseEntity<?> getBookingsDetail(@PathVariable Long serviceBookingId) {
        return ResponseEntity.ok(bookingService.viewBookingDetails(serviceBookingId));
    }

    @PostMapping("/bookings/update-status")
    public ResponseEntity<?> updateBookingStatus(@RequestBody BookingStatusUpdateDTO dto) {
        return ResponseEntity.ok(bookingService.updateBookingStatus(dto));
    }
}
