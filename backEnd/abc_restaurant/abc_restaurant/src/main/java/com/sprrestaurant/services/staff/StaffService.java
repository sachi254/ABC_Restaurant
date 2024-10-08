package com.sprrestaurant.services.staff;

import com.sprrestaurant.dtos.CategoryDto;
import com.sprrestaurant.dtos.ProductDto;
import com.sprrestaurant.dtos.ReservationDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StaffService {


    List<CategoryDto> getAllCategories();

    List<CategoryDto> getAllCategoriesByName(String title);

    List<ProductDto> getProductsByCategory(Long categoryId);

    List<ProductDto> getProductsByCategoryAndTitle(Long categoryId, String title);


    List<ReservationDto> getReservations();

    ReservationDto changeReservationStatus(Long reservationId, String status);
}
