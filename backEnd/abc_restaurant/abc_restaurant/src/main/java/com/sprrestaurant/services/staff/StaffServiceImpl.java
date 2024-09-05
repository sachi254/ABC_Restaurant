package com.sprrestaurant.services.staff;


import com.sprrestaurant.dtos.CategoryDto;
import com.sprrestaurant.dtos.ProductDto;
import com.sprrestaurant.dtos.ReservationDto;
import com.sprrestaurant.entities.Category;
import com.sprrestaurant.entities.Product;
import com.sprrestaurant.entities.Reservation;
import com.sprrestaurant.enums.ReservationStatus;
import com.sprrestaurant.repositories.CategoryRepository;
import com.sprrestaurant.repositories.ProductRepository;
import com.sprrestaurant.repositories.ReservationRepository;
import com.sprrestaurant.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StaffServiceImpl implements StaffService{

    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;
    private final ReservationRepository reservationRepository;


    @Override
    public List<CategoryDto> getAllCategories() {
        return categoryRepository.findAll().stream().map(Category::getCategoryDto).collect(Collectors.toList());
    }

    @Override
    public List<CategoryDto> getAllCategoriesByName(String title) {
        return categoryRepository.findAllByNameContaining(title).stream().map(Category::getCategoryDto).collect(Collectors.toList());
    }

    @Override
    public List<ProductDto> getProductsByCategory(Long categoryId) {
        return productRepository.findAllByCategoryId(categoryId).stream().map(Product::getProductDto).collect(Collectors.toList());
    }

    @Override
    public List<ProductDto> getProductsByCategoryAndTitle(Long categoryId, String title) {
        return productRepository.findAllByCategoryIdAndNameContaining(categoryId,title).stream().map(Product::getProductDto).collect(Collectors.toList());
    }


    //get reservations

    @Override
    public List<ReservationDto> getReservations() {
        return reservationRepository.findAll().stream().map(Reservation::getReservationDto).collect(Collectors.toList());
    }

    // update the Reservation status
    @Override
    public ReservationDto changeReservationStatus(Long reservationId, String status) {
        Optional<Reservation> optionalReservation = reservationRepository.findById(reservationId);
        if (optionalReservation.isPresent()){
            Reservation existingReservation = optionalReservation.get();
            if(Objects.equals(status, "Approved")){
                existingReservation.setReservationStatus(ReservationStatus.Approved);
            }else{
                existingReservation.setReservationStatus(ReservationStatus.Cancelled);
            }
            Reservation updatedReservation = reservationRepository.save(existingReservation);
            ReservationDto updatedReservationDto = new ReservationDto();
            updatedReservationDto.setId(updatedReservation.getId());
            return updatedReservationDto;

        }
        return null;
    }


}
