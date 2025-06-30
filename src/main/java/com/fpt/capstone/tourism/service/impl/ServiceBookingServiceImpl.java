package com.fpt.capstone.tourism.service.impl;

import com.fpt.capstone.tourism.dto.common.BookingRequestDTO;
import com.fpt.capstone.tourism.dto.common.GeneralResponse;
import com.fpt.capstone.tourism.dto.request.booking.ServiceBookingRequestDTO;
import com.fpt.capstone.tourism.dto.response.PublicServiceDTO;
import com.fpt.capstone.tourism.dto.response.booking.ServiceBookingDetailResponseDTO;
import com.fpt.capstone.tourism.dto.response.service.ActivityResponseDTO;
import com.fpt.capstone.tourism.dto.response.service.MealResponseDTO;
import com.fpt.capstone.tourism.exception.common.BusinessException;
import com.fpt.capstone.tourism.helper.IHelper.BookingHelper;
import com.fpt.capstone.tourism.model.*;
import com.fpt.capstone.tourism.model.enums.*;
import com.fpt.capstone.tourism.repository.*;
import com.fpt.capstone.tourism.service.ServiceBookingService;
import com.fpt.capstone.tourism.service.VNPayService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ServiceBookingServiceImpl implements ServiceBookingService {

    private final CartItemRepository cartItemRepository;
    private final ServiceBookingRepository serviceBookingRepository;
    private final ServiceBookingDetailRepository serviceBookingDetailRepository;
    private final ServiceRepository serviceRepository;
    private final TransactionRepository transactionRepository;
    private final CostAccountRepository costAccountRepository;

    private final BookingHelper bookingHelper;
    private final VNPayService vnPayService;

    @Value("${backend.base-url}")
    private String backendBaseUrl;

    @Override
    @Transactional
    public GeneralResponse<?> submitBooking(ServiceBookingRequestDTO dto) {
        try {

            List<CartItem> items = cartItemRepository.findCartItemsByUserId(dto.getUserId());


            String baseUrl = backendBaseUrl + "/public/service-booking";

            String bookingCode = bookingHelper.generateBookingCode(dto.getUserId(), 5L, dto.getUserId());

            String paymentUrl = vnPayService.generatePaymentUrl(dto.getTotal(), bookingCode, baseUrl);

            ServiceBooking serviceBooking = ServiceBooking.builder()
                    .user(User.builder().id(dto.getUserId()).build())
                    .status(BookingStatus.PENDING)
                    .deleted(false)
                    .totalPrice(dto.getTotal())
                    .bookingCode(bookingCode)
                    .paymentUrl(paymentUrl)
                    .build();

            // Create a cost account for the booking
            Transaction transaction = Transaction.builder()
                    .amount(dto.getTotal())
                    .paymentMethod(PaymentMethod.BANKING)
                    .notes("none")
                    .receivedBy("System")
                    .paidBy("User")
                    .booking(TourBooking.builder().id(7L).build())
                    .transactionStatus(TransactionStatus.PAID)
                    .category(TransactionType.RECEIPT)
                    .build();

            Transaction savedTransaction = transactionRepository.save(transaction);

            CostAccount costAccount = CostAccount.builder()
                    .transaction(savedTransaction)
                    .status(CostAccountStatus.PAID)
                    .amount(dto.getTotal())
                    .quantity(1)
                    .finalAmount(dto.getTotal())
                    .discount(0)
                    .content("Service Booking Payment")
                    .build();

            costAccountRepository.save(costAccount);

            ServiceBooking savedServiceBooking = serviceBookingRepository.save(serviceBooking);

            List<ServiceBookingDetail> details = new ArrayList<>();

            for(CartItem cartItem: items) {
                ServiceBookingDetail serviceBookingDetail = ServiceBookingDetail.builder()
                        .bookingService(savedServiceBooking)
                        .service(cartItem.getService())
                        .checkInDate(cartItem.getCheckInDate())
                        .checkOutDate(cartItem.getCheckOutDate())
                        .deleted(false)
                        .quantity(cartItem.getQuantity())
                        .build();
                details.add(serviceBookingDetail);
            }

            serviceBookingDetailRepository.saveAll(details);


            cartItemRepository.deleteAll(items);

            return GeneralResponse.of(bookingCode);
        } catch (Exception e) {
            throw BusinessException.of(HttpStatus.INTERNAL_SERVER_ERROR, "Create Booking Failed", e);
        }
    }

    @Override
    public GeneralResponse<?> bookingDetail(String bookingCode) {
        try {

            if (bookingCode == null || bookingCode.isEmpty()) {
                throw BusinessException.of(HttpStatus.BAD_REQUEST, "Booking code is required");
            }
            ServiceBooking serviceBooking = serviceBookingRepository.findByBookingCode(bookingCode)
                    .orElseThrow(() -> BusinessException.of(HttpStatus.NOT_FOUND, "Booking not found"));

            List<PublicServiceDTO> rooms = serviceRepository.findRoomsInBooking(bookingCode);
            List<MealResponseDTO> meals = serviceRepository.findMealsInBooking(bookingCode);
            List<ActivityResponseDTO> activities = serviceRepository.findActivitiesInBooking(bookingCode);

            ServiceBookingDetailResponseDTO dto = ServiceBookingDetailResponseDTO.builder()
                    .rooms(rooms)
                    .meals(meals)
                    .activities(activities)
                    .paymentUrl(serviceBooking.getPaymentUrl())
                    .bookingStatus(serviceBooking.getStatus())
                    .build();
            return GeneralResponse.of(dto, "Booking Detail Successfully");
        } catch (Exception e) {
            throw BusinessException.of(HttpStatus.INTERNAL_SERVER_ERROR, "Create Booking Failed", e);
        }
    }

    @Override
    public void confirmPayment(int paymentStatus, String orderInfo) {
        try {
            if (orderInfo == null || orderInfo.isEmpty()) {
                throw BusinessException.of(HttpStatus.BAD_REQUEST, "Order info is required");
            }

            ServiceBooking serviceBooking = serviceBookingRepository.findByBookingCode(orderInfo)
                    .orElseThrow(() -> BusinessException.of(HttpStatus.NOT_FOUND, "Booking not found"));

            if (paymentStatus == 1) {
                serviceBooking.setStatus(BookingStatus.SUCCESS);
            } else {
                serviceBooking.setStatus(BookingStatus.PENDING);
            }
            serviceBookingRepository.save(serviceBooking);
        } catch (Exception e) {
            throw BusinessException.of(HttpStatus.INTERNAL_SERVER_ERROR, "Create Booking Failed", e);
        }
    }
}
