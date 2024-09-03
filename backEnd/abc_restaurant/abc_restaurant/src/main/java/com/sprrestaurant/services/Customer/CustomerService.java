package com.sprrestaurant.services.Customer;

import com.sprrestaurant.dtos.CategoryDto;
import com.sprrestaurant.dtos.ProductDto;
import com.sprrestaurant.dtos.ReservationDto;

import java.util.List;

public interface CustomerService {


    List<CategoryDto> getAllCategories();

    List<CategoryDto> getAllCategoriesByName(String title);

    List<ProductDto> getProductsByCategory(Long categoryId);

    List<ProductDto> getProductsByCategoryAndTitle(Long categoryId, String title);

    ReservationDto postReservation(ReservationDto reservationDto);

    List<ReservationDto> getReservationsByUser(Long customerId);
}
