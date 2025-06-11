package com.fpt.capstone.tourism.model;

import com.fpt.capstone.tourism.model.enums.BookingStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ServiceBooking extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "booking_code", unique = true)
    private String bookingCode;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user; // Nếu đã có entity User

    @Column(name = "total_price")
    private double totalPrice;

    @Enumerated(EnumType.STRING)
    private BookingStatus status; // PENDING, CONFIRMED, CANCELED

    @Column(name = "is_deleted")
    private Boolean deleted = false;

    @Column(name = "payment_url", columnDefinition = "text")
    private String paymentUrl;

}
