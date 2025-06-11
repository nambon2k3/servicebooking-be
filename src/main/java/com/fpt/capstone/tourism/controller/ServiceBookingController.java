package com.fpt.capstone.tourism.controller;


import com.fpt.capstone.tourism.dto.common.BookingRequestDTO;
import com.fpt.capstone.tourism.dto.common.GeneralResponse;
import com.fpt.capstone.tourism.dto.request.booking.ServiceBookingRequestDTO;
import com.fpt.capstone.tourism.service.ServiceBookingService;
import com.fpt.capstone.tourism.service.VNPayService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@RestController
@RequiredArgsConstructor
@RequestMapping("/public/service-booking")
public class ServiceBookingController {

    private final ServiceBookingService serviceBookingService;
    private final VNPayService vnPayService;

    @Value("${frontend.base-url}")
    private String frontendBaseUrl;


    @PostMapping("/submit")
    public ResponseEntity<GeneralResponse<?>> submitBooking(@RequestBody ServiceBookingRequestDTO dto){
        return ResponseEntity.ok(serviceBookingService.submitBooking(dto));
    }

    @GetMapping("/details/{bookingCode}")
    public ResponseEntity<GeneralResponse<?>> bookingDetail(@PathVariable String bookingCode){
        return ResponseEntity.ok(serviceBookingService.bookingDetail(bookingCode));
    }




    @GetMapping("/vnpay")
    public RedirectView getVnPayPayment(HttpServletRequest request) {
        int paymentStatus = vnPayService.orderReturn(request);
        String orderInfo = request.getParameter("vnp_OrderInfo");


        String redirectUrl = String.format(
                frontendBaseUrl + "/customer/service-booking-confirm/%s?status=%s",
                URLEncoder.encode(orderInfo, StandardCharsets.UTF_8),
                paymentStatus == 1 ? "success" : "fail"
        );

        serviceBookingService.confirmPayment(paymentStatus, orderInfo);

        return new RedirectView(redirectUrl);
    }


}
