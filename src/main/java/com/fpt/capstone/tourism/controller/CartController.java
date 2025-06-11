package com.fpt.capstone.tourism.controller;

import com.fpt.capstone.tourism.dto.common.GeneralResponse;
import com.fpt.capstone.tourism.dto.request.cart.AddToCartRequestDTO;
import com.fpt.capstone.tourism.dto.request.cart.UpdateCartRequestDTO;
import com.fpt.capstone.tourism.dto.response.TourBookingDataResponseDTO;
import com.fpt.capstone.tourism.repository.CartItemRepository;
import com.fpt.capstone.tourism.service.CartItemService;
import lombok.RequiredArgsConstructor;
import org.hibernate.sql.Update;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequiredArgsConstructor
@RequestMapping("/public/cart")
public class CartController {

    private final CartItemService cartItemService;


    @PostMapping("/add")
    public ResponseEntity<GeneralResponse<?>> addToCart(@RequestBody AddToCartRequestDTO dto){
        return ResponseEntity.ok(cartItemService.addToCart(dto));
    }

    @GetMapping("/details/{userId}")
    public ResponseEntity<GeneralResponse<?>> cartDetails(@PathVariable Long userId){
        return ResponseEntity.ok(cartItemService.cartDetails(userId));
    }


    @DeleteMapping("/delete/{cartItemId}")
    public ResponseEntity<GeneralResponse<?>> deleteCartItem(@PathVariable Long cartItemId){
        return ResponseEntity.ok(cartItemService.deleteCartItem(cartItemId));
    }

    @PostMapping("/update")
    public ResponseEntity<GeneralResponse<?>> updateItem(@RequestBody UpdateCartRequestDTO dto){
        return ResponseEntity.ok(cartItemService.updateCartItem(dto));
    }

}
