package com.fpt.capstone.tourism.service.impl;

import com.fpt.capstone.tourism.dto.common.GeneralResponse;
import com.fpt.capstone.tourism.dto.common.WishlistDTO;
import com.fpt.capstone.tourism.exception.common.BusinessException;
import com.fpt.capstone.tourism.model.*;
import com.fpt.capstone.tourism.repository.*;
import com.fpt.capstone.tourism.service.WishlistService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import static com.fpt.capstone.tourism.constants.Constants.Message.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class WishlistServiceImpl implements WishlistService {
    private final WishlistRepository wishlistRepository;
    private final TourImageRepository tourImageRepository;
    private final TourRepository tourRepository;
    private final UserRepository userRepository;

    private final ServiceProviderRepository serviceProviderRepository;

    @Override
    public GeneralResponse<?> getUserListWishlist() {
        try {
            Long userId = null;
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            if (auth != null && auth.getName() != null) {
                userId = userRepository.findIdByUsername(auth.getName());
            }

            List<Object[]> rawWishlist = wishlistRepository.findWishlistByUserId(userId);
            List<Long> providerIds = rawWishlist.stream()
                    .map(row -> ((Number) row[1]).longValue())
                    .distinct()
                    .toList();
            // Map: tourId -> name
            List<ServiceProvider> providers = serviceProviderRepository.findServiceProvidersByIds(providerIds);

            List<WishlistDTO>resultDTOs =  rawWishlist.stream().map(row -> {
                Long wishlistId = ((Number) row[0]).longValue();
                Long itemId = ((Number) row[1]).longValue();
                String itemType = (String) row[2];
                return WishlistDTO.builder()
                        .id(wishlistId)
                        .itemId(itemId)
                        .itemType(itemType)
                        .tourName(providers.stream()
                                .filter(provider -> provider.getId().equals(itemId))
                                .findFirst()
                                .map(ServiceProvider::getName)
                                .orElseThrow(() -> BusinessException.of("Không tìm thấy tên tour")))
                        .tourImageUrl(
                                providers.stream()
                                        .filter(provider -> provider.getId().equals(itemId))
                                        .findFirst()
                                        .map(ServiceProvider::getImageUrl)
                                        .orElseThrow(() -> BusinessException.of("Không tìm thấy ảnh tour"))
                        ) // hoặc get list luôn nếu muốn
                        .build();
            }).toList();
            return new GeneralResponse<>(HttpStatus.OK.value(), "Lấy wishlist thành công", resultDTOs);
        } catch (Exception ex) {
            throw BusinessException.of("Lấy wishlist thất bại", ex);
        }
    }

    @Override
    public GeneralResponse<?> addWishlist(Long itemId) {
        try {
            User user = getCurrentUser();
            Wishlist dbWishlist = wishlistRepository.findByItemIdAndUserId(user.getId(), itemId);
            if(dbWishlist != null){
                throw BusinessException.of("Item đã có trong danh sách yêu thích");
            }

            ServiceProvider provider = serviceProviderRepository.findById(itemId).orElseThrow(
                    () -> BusinessException.of("Không tìm thấy nhà cung cấp dịch vụ")
            );

            Wishlist wishlist = Wishlist.builder()
                    .itemId(itemId)
                    .itemType(provider.getServiceCategories().stream()
                            .map(ServiceCategory::getCategoryName)
                            .collect(Collectors.joining(",")))
                    .user(user)
                    .build();
            wishlistRepository.save(wishlist);

            WishlistDTO wishlistDTO = WishlistDTO.builder()
                    .itemId(wishlist.getItemId())
                    .itemType(wishlist.getItemType())
                    .tourName(provider.getName())
                    .tourImageUrl(provider.getImageUrl())
                    .build();
            return new GeneralResponse<>(HttpStatus.OK.value(), "Lấy wishlist thành công", wishlistDTO);
        } catch (Exception ex) {
            throw BusinessException.of("Thêm wishlist thất bại", ex);
        }
    }

    @Override
    public GeneralResponse<?> deleteWishlist(Long wishlistId) {
        try {
            User user = getCurrentUser();
            Wishlist wishlist = wishlistRepository.findById(wishlistId).orElseThrow(
                    () -> BusinessException.of(WISHLIST_NOT_FOUND)
            );
            if(user.getId() != wishlist.getUser().getId()){
                throw BusinessException.of(NO_PERMISSION_TO_DELETE);
            }
            wishlistRepository.deleteById(wishlistId);
            ServiceProvider provider = serviceProviderRepository.findById(wishlist.getItemId()).orElseThrow(
                    () -> BusinessException.of(TOUR_NOT_FOUND)
            );
            WishlistDTO wishlistDTO = WishlistDTO.builder()
                    .id(wishlist.getId())
                    .itemId(wishlist.getItemId())
                    .itemType(wishlist.getItemType())
                    .tourName(Optional.ofNullable(provider.getName()).orElseThrow(null))
                    .tourImageUrl(Optional.ofNullable(provider.getImageUrl()).orElseThrow(null))
                    .build();
            return new GeneralResponse<>(HttpStatus.OK.value(), DELETE_WISHLIST_SUCCESS, wishlistDTO);
        } catch (Exception ex) {
            throw BusinessException.of(DELETE_WISHLIST_FAIL, ex);
        }
    }

    private User getCurrentUser() {
        User user = null;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getName() != null) {
            user = userRepository.findByUsername(authentication.getName())
                    .orElseThrow(() -> BusinessException.of("Không tìm thấy người dùng"));
        }
        return user;
    }
}
