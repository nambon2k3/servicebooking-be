package com.fpt.capstone.tourism.service;

import com.fpt.capstone.tourism.dto.common.GeneralResponse;
import com.fpt.capstone.tourism.dto.request.cart.AddToCartRequestDTO;
import com.fpt.capstone.tourism.dto.request.cart.UpdateCartRequestDTO;

public interface CartItemService {
    GeneralResponse<?> addToCart(AddToCartRequestDTO dto);
    GeneralResponse<?> cartDetails(Long userId);

    GeneralResponse<?> deleteCartItem(Long cartItemId);

    GeneralResponse<?> updateCartItem(UpdateCartRequestDTO dto);
}
