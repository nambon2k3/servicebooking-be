package com.fpt.capstone.tourism.repository;

import com.fpt.capstone.tourism.dto.response.cart.CartItemActivityResponseDTO;
import com.fpt.capstone.tourism.dto.response.cart.CartItemMealResponseDTO;
import com.fpt.capstone.tourism.dto.response.cart.CartItemRoomResponseDTO;
import com.fpt.capstone.tourism.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    Optional<CartItem> findCartItemByUserIdAndServiceId(Long userId, Long serviceId);


    List<CartItem> findCartItemsByUserId(Long userId);


    @Query("""
    SELECT new com.fpt.capstone.tourism.dto.response.cart.CartItemRoomResponseDTO(
        ci.id, s.id, s.name, s.sellingPrice, s.imageUrl,
        r.id, r.capacity, r.availableQuantity, r.facilities,
        ci.quantity, ci.checkInDate, ci.checkOutDate
    )
    FROM CartItem ci
    JOIN Service s ON ci.service.id = s.id
    JOIN Room r ON r.service.id = s.id
    WHERE ci.user.id = :userId AND s.serviceCategory.id = 1
""")
    List<CartItemRoomResponseDTO> findCartItemsRoomByUserId(@Param("userId") Long userId);



    @Query("""
    SELECT new com.fpt.capstone.tourism.dto.response.cart.CartItemMealResponseDTO(
        ci.id, s.id, s.name, s.sellingPrice, s.imageUrl, m.type, m.mealDetail, ci.quantity, ci.checkInDate, ci.checkOutDate
    )
    FROM CartItem ci
    JOIN Service s ON ci.service.id = s.id
    JOIN Meal m ON s.id = m.service.id
    WHERE ci.user.id = :userId AND s.serviceCategory.id = 2
""")
    List<CartItemMealResponseDTO> findCartItemsMealByUserId(@Param("userId") Long userId);



    @Query("""
        SELECT new com.fpt.capstone.tourism.dto.response.cart.CartItemActivityResponseDTO(
            ci.id, s.id, s.name, s.sellingPrice, s.imageUrl, ci.quantity, ci.checkInDate, ci.checkOutDate
        )
        FROM CartItem ci
        JOIN Service s ON ci.service.id = s.id
        WHERE ci.user.id = :userId AND s.serviceCategory.id = 4
    """)
    List<CartItemActivityResponseDTO> findCartItemsActivityByUserId(@Param("userId") Long userId);



}
