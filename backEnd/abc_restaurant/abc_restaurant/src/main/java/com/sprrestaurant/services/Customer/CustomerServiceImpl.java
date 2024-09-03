package com.sprrestaurant.services.Customer;


import com.sprrestaurant.dtos.CategoryDto;
import com.sprrestaurant.entities.Category;
import com.sprrestaurant.repositories.CategoryRepository;
import com.sprrestaurant.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService{


    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;


    @Override
    public List<CategoryDto> getAllCategories() {
        return categoryRepository.findAll().stream().map(Category::getCategoryDto).collect(Collectors.toList());
    }

    @Override
    public List<CategoryDto> getAllCategoriesByName(String title) {
        return categoryRepository.findAllByNameContaining(title).stream().map(Category::getCategoryDto).collect(Collectors.toList());
    }
}
