package com.fpt.capstone.tourism.service;

import com.fpt.capstone.tourism.dto.common.BookingRequestDTO;
import com.fpt.capstone.tourism.dto.common.GeneralResponse;
import com.fpt.capstone.tourism.dto.request.booking.ServiceBookingRequestDTO;

public interface ServiceBookingService {
    GeneralResponse<?> submitBooking(ServiceBookingRequestDTO dto);

    GeneralResponse<?> bookingDetail(String bookingCode);

    void confirmPayment(int paymentStatus, String orderInfo);
}
