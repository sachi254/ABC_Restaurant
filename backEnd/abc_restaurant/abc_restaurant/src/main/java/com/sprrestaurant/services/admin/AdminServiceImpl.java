package com.sprrestaurant.services.admin;


import com.fasterxml.jackson.databind.util.BeanUtil;
import com.sprrestaurant.dtos.CategoryDto;
import com.sprrestaurant.dtos.ProductDto;
import com.sprrestaurant.entities.Category;
import com.sprrestaurant.entities.Product;
import com.sprrestaurant.repositories.CategoryRepository;

import com.sprrestaurant.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {


    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;


    @Override
    public CategoryDto postCategory(CategoryDto categoryDto) throws IOException {

        Category category = new Category();
        category.setName(categoryDto.getName());
        category.setDescription(categoryDto.getDescription());
        category.setImg(categoryDto.getImg().getBytes());
        Category createdCategory = categoryRepository.save(category);
        CategoryDto createdCategoryDto = new CategoryDto();
        createdCategoryDto.setId(createdCategory.getId());
        return createdCategoryDto;
    }

    @Override
    public List<CategoryDto> getAllCategories() {
        return categoryRepository.findAll().stream().map(Category::getCategoryDto).collect(Collectors.toList());
    }

    @Override
    public List<CategoryDto> getAllCategoriesByTitle(String title) {
        return categoryRepository.findAllByNameContaining(title).stream().map(Category::getCategoryDto).collect(Collectors.toList());

    }

    // Start the product functions

    @Override
    public ProductDto postProduct(Long categoryId, ProductDto productDto) throws IOException {
        Optional<Category> optionalCategory = categoryRepository.findById(categoryId);
        if(optionalCategory.isPresent()){
            Product product = new Product();
            BeanUtils.copyProperties(productDto, product);
            product.setImg(productDto.getImg().getBytes());
            product.setCategory(optionalCategory.get());
            Product createdProduct = productRepository.save(product);
            ProductDto createdProductDto = new ProductDto();
            createdProductDto.setId(createdProduct.getId());
            return createdProductDto;
        }
        return null;
    }

}
