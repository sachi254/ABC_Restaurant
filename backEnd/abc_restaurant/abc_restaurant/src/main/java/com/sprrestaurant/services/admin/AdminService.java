package com.sprrestaurant.services.admin;

import com.sprrestaurant.dtos.CategoryDto;
import com.sprrestaurant.dtos.ProductDto;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public interface AdminService {


    CategoryDto postCategory(CategoryDto categoryDto) throws IOException;

    List<CategoryDto> getAllCategories();

    List<CategoryDto> getAllCategoriesByTitle(String title);

    ProductDto postProduct(Long categoryId, ProductDto productDto) throws IOException;

    List<ProductDto> getAllProductsByCategory(Long categoryId);


    List<ProductDto> getProductsByCategoryAndTitle(Long categoryId, String title);

    void deleteProduct(Long productId);

    ProductDto getProductById(Long productId);

    ProductDto updateProduct(Long productId, ProductDto productDto)throws IOException;


}
