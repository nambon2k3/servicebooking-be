package com.fpt.capstone.tourism.repository;

import com.fpt.capstone.tourism.dto.common.MealDetailResponseDTO;
import com.fpt.capstone.tourism.dto.common.RevenueChartDTO;
import com.fpt.capstone.tourism.dto.common.RoomDetailResponseDTO;
import com.fpt.capstone.tourism.dto.common.ServiceBookingDetailDTO;
import com.fpt.capstone.tourism.model.ServiceBooking;
import com.fpt.capstone.tourism.model.User;
import com.fpt.capstone.tourism.model.enums.BookingStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface ServiceBookingRepository extends JpaRepository<ServiceBooking, Long>, JpaSpecificationExecutor<ServiceBooking> {


    Optional<ServiceBooking> findByBookingCode(String bookingCode);
    @Query("""
    SELECT u.id
    FROM ServiceBooking sb
    JOIN sb.user u
    WHERE sb.bookingCode = :bookingCode
""")
    Long findUserIdByBookingCode(String bookingCode);

    @Query("""
    SELECT new com.fpt.capstone.tourism.dto.common.RoomDetailResponseDTO(
        svd.service.id, svd.service.name, svd.service.sellingPrice, svd.service.nettPrice,
        svd.service.imageUrl,
        svd.quantity, svd.checkInDate, svd.checkOutDate,
        r.id, r.capacity, r.availableQuantity, r.facilities
    )
    FROM ServiceBookingDetail svd
    JOIN Service s ON svd.service.id = s.id
    JOIN Room r ON r.service.id = s.id
    WHERE svd.bookingService.id = :id AND s.serviceCategory.id = 1
""")
    List<RoomDetailResponseDTO> findHotelItemsByBookingId(Long id);

    @Query("""
    SELECT new com.fpt.capstone.tourism.dto.common.MealDetailResponseDTO(
        svd.service.id, svd.service.name, svd.service.sellingPrice, svd.service.nettPrice,
        svd.service.imageUrl,
        svd.quantity, svd.checkInDate, svd.checkOutDate,
        m.type, m.mealDetail
    )
    FROM ServiceBookingDetail svd
    JOIN Service s ON svd.service.id = s.id
    JOIN Meal m ON s.id = m.service.id
    WHERE svd.bookingService.id = :id AND s.serviceCategory.id = 2
""")
    List<MealDetailResponseDTO> findMealItemsByBookingId(Long id);

    @Query("""
                SELECT new com.fpt.capstone.tourism.dto.common.ServiceBookingDetailDTO(
                    svd.service.id, svd.service.name, svd.service.sellingPrice, svd.service.nettPrice,
                svd.service.imageUrl,
                svd.quantity, svd.checkInDate, svd.checkOutDate
                )
                FROM ServiceBookingDetail svd
            JOIN Service s ON svd.service.id = s.id
                WHERE svd.bookingService.id = :id AND s.serviceCategory.id = 4
            """)
    List<ServiceBookingDetailDTO> findActivityItemsByBookingId(Long id);

    @Query(value = "select * from service_booking where id = ?1", nativeQuery = true)
    ServiceBooking findByBookingId(Long id);
}
