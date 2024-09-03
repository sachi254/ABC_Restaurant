package com.sprrestaurant.services.Customer;


import com.sprrestaurant.dtos.CategoryDto;
import com.sprrestaurant.dtos.ProductDto;
import com.sprrestaurant.dtos.ReservationDto;
import com.sprrestaurant.entities.Category;
import com.sprrestaurant.entities.Product;
import com.sprrestaurant.entities.Reservation;
import com.sprrestaurant.entities.User;
import com.sprrestaurant.enums.ReservationStatus;
import com.sprrestaurant.repositories.CategoryRepository;
import com.sprrestaurant.repositories.ProductRepository;
import com.sprrestaurant.repositories.ReservationRepository;
import com.sprrestaurant.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService{


    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;
    private final ReservationRepository reservationRepository;
    private final UserRepository userRepository;

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

    @Override
    public ReservationDto postReservation(ReservationDto reservationDto) {
        Optional<User> optionalUser = userRepository.findById(reservationDto.getCustomerId());
        if(optionalUser.isPresent()){
            Reservation reservation = new Reservation();
            reservation.setTableType(reservationDto.getTableType());
            reservation.setDateTime(reservationDto.getDateTime());
            reservation.setDescription(reservationDto.getDescription());
            reservation.setUser(optionalUser.get());
            reservation.setReservationStatus(ReservationStatus.Pending);
            Reservation postedReservation = reservationRepository.save(reservation);
            ReservationDto postedReservationDto =new ReservationDto();
            postedReservationDto.setId(postedReservation.getId());
            return postedReservationDto;
        }
        return null;
    }

    @Override
    public List<ReservationDto> getReservationsByUser(Long customerId) {
        return reservationRepository.findAllByUserId(customerId).stream().map(Reservation::getReservationDto).collect(Collectors.toList());
    }
}
