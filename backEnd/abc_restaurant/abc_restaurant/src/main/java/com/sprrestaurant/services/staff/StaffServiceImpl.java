package com.sprrestaurant.services.staff;


import com.sprrestaurant.dtos.CategoryDto;
import com.sprrestaurant.dtos.ProductDto;
import com.sprrestaurant.entities.Category;
import com.sprrestaurant.entities.Product;
import com.sprrestaurant.repositories.CategoryRepository;
import com.sprrestaurant.repositories.ProductRepository;
import com.sprrestaurant.repositories.ReservationRepository;
import com.sprrestaurant.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StaffServiceImpl implements StaffService{

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


}
