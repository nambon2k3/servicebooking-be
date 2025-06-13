package com.fpt.capstone.tourism.dto.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoomDetailResponseDTO extends ServiceBookingDetailDTO{
    private Long roomId;
    private Integer capacity; // Số khách tối đa trong phòng
    private Integer availableQuantity; // Số lượng phòng còn lại
    private String facilities; // Danh sách các dịch vụ trong phòng (bồn tắm, tủ lạnh, wifi)

    public RoomDetailResponseDTO(Long serviceId, String name, double sellingPrice, double nettPrice, String imageUrl, int quantity, LocalDateTime checkInDate, LocalDateTime checkOutDate, Long roomId, Integer capacity, Integer availableQuantity, String facilities) {
        super(serviceId, name, sellingPrice, nettPrice, imageUrl, quantity, checkInDate, checkOutDate);
        this.roomId = roomId;
        this.capacity = capacity;
        this.availableQuantity = availableQuantity;
        this.facilities = facilities;
    }
}
