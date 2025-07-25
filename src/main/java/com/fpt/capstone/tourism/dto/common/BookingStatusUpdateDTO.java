package com.fpt.capstone.tourism.dto.common;

import com.fpt.capstone.tourism.model.ServiceBooking;
import com.fpt.capstone.tourism.model.enums.BookingStatus;
import com.fpt.capstone.tourism.model.enums.TourBookingStatus;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BookingStatusUpdateDTO {
    @NotNull(message = "booking ID không được trống")
    private Long id;

    @NotNull(message = "Chưa truyền trạng thái")
    private BookingStatus bookingStatus;
}
