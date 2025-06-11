package com.fpt.capstone.tourism.service.impl;

import com.fpt.capstone.tourism.dto.common.GeneralResponse;
import com.fpt.capstone.tourism.dto.request.cart.AddToCartRequestDTO;
import com.fpt.capstone.tourism.dto.request.cart.UpdateCartRequestDTO;
import com.fpt.capstone.tourism.dto.response.cart.CartItemActivityResponseDTO;
import com.fpt.capstone.tourism.dto.response.cart.CartItemMealResponseDTO;
import com.fpt.capstone.tourism.dto.response.cart.CartItemRoomResponseDTO;
import com.fpt.capstone.tourism.dto.response.cart.CartResponseDTO;
import com.fpt.capstone.tourism.exception.common.BusinessException;
import com.fpt.capstone.tourism.model.CartItem;
import com.fpt.capstone.tourism.model.User;
import com.fpt.capstone.tourism.repository.CartItemRepository;
import com.fpt.capstone.tourism.repository.ServiceRepository;
import com.fpt.capstone.tourism.service.CartItemService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static com.fpt.capstone.tourism.constants.Constants.Message.UPDATE_USER_FAIL_MESSAGE;

@Service
@RequiredArgsConstructor
public class CartItemServiceImpl implements CartItemService {

    @PersistenceContext
    private EntityManager entityManager;

    private final CartItemRepository cartItemRepository;
    private final ServiceRepository serviceRepository;

    @Override
    @Transactional
    public GeneralResponse<?> addToCart(AddToCartRequestDTO dto) {

        com.fpt.capstone.tourism.model.Service service = entityManager.getReference(com.fpt.capstone.tourism.model.Service.class, dto.getServiceId());

        try {
            Optional<CartItem> item = cartItemRepository.findCartItemByUserIdAndServiceId(dto.getUserId(), dto.getServiceId());

            if(item.isEmpty()) {
                CartItem cartItem = CartItem.builder()
                        .quantity(dto.getQuantity())
                        .user(User.builder().id(dto.getUserId()).build())
                        .service(service)
                        .checkInDate(LocalDateTime.now())
                        .checkOutDate(LocalDateTime.now())
                        .build();
                cartItemRepository.save(cartItem);
            }

            return GeneralResponse.of(dto, "Add to cart Success");
        } catch (Exception e) {
            throw BusinessException.of(HttpStatus.INTERNAL_SERVER_ERROR, "Add to cart Failed", e);
        }
    }

    @Override
    public GeneralResponse<?> cartDetails(Long userId) {
        try {

            List<CartItemRoomResponseDTO> hotelItems = cartItemRepository.findCartItemsRoomByUserId(userId);
            List<CartItemMealResponseDTO> mealItems = cartItemRepository.findCartItemsMealByUserId(userId);
            List<CartItemActivityResponseDTO> activityItems = cartItemRepository.findCartItemsActivityByUserId(userId);

            CartResponseDTO cartResponseDTO = CartResponseDTO.builder()
                    .hotelItems(hotelItems)
                    .activityItems(activityItems)
                    .mealItems(mealItems)
                    .build();

            return GeneralResponse.of(cartResponseDTO, "Get cart Success");
        } catch (Exception e) {
            throw BusinessException.of(HttpStatus.INTERNAL_SERVER_ERROR, "Get cart Failed", e);
        }
    }

    @Override
    @Transactional
    public GeneralResponse<?> deleteCartItem(Long cartItemId) {
        try {
            CartItem cartItem = cartItemRepository.findById(cartItemId).orElseThrow();
            cartItemRepository.delete(cartItem);
            return GeneralResponse.of(cartItemId, "Delete cart Success");
        } catch (Exception e) {
            throw BusinessException.of(HttpStatus.INTERNAL_SERVER_ERROR, "Delete cart Failed", e);
        }
    }

    @Override
    @Transactional
    public GeneralResponse<?> updateCartItem(UpdateCartRequestDTO dto) {
        try {
            CartItem cartItem = cartItemRepository.findById(dto.getCartItemId()).orElseThrow();

            cartItem.setCheckInDate(dto.getCheckInDate());
            cartItem.setCheckOutDate(dto.getCheckOutDate());
            cartItem.setQuantity(dto.getQuantity());
            cartItemRepository.save(cartItem);
            return GeneralResponse.of(dto, "Update cart Success");
        } catch (Exception e) {
            throw BusinessException.of(HttpStatus.INTERNAL_SERVER_ERROR, "Update cart Failed", e);
        }
    }
}
