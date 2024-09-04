package com.sprrestaurant.services.admin;


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
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {


    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;
    private final ReservationRepository reservationRepository;


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

    @Override
    public List<ProductDto> getAllProductsByCategory(Long categoryId) {
        return productRepository.findAllByCategoryId(categoryId).stream().map(Product::getProductDto).collect(Collectors.toList());

    }

    @Override
    public List<ProductDto> getProductsByCategoryAndTitle(Long categoryId, String title) {
        return productRepository.findAllByCategoryIdAndNameContaining(categoryId,title).stream().map(Product::getProductDto).collect(Collectors.toList());
    }

    @Override
    public void deleteProduct(Long productId) {
        Optional<Product> optionalProduct = productRepository.findById(productId);
        if(optionalProduct.isPresent()){
            productRepository.delete(optionalProduct.get());
        }

    }

    @Override
    public ProductDto getProductById(Long productId) {
        Optional<Product> optionalProduct = productRepository.findById(productId);
        return optionalProduct.map(Product::getProductDto).orElse(null);
    }

    @Override
    public ProductDto updateProduct(Long productId, ProductDto productDto) throws IOException {
        Optional<Product> optionalProduct = productRepository.findById(productId);
        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            BeanUtils.copyProperties(productDto, product, "id", "category");
            if (productDto.getImg() != null) {
                product.setImg(productDto.getImg().getBytes());
            }
            Product updatedProduct = productRepository.save(product);
            ProductDto updatedProductDto = new ProductDto();
            BeanUtils.copyProperties(updatedProduct, updatedProductDto);
            return updatedProductDto;
        } else {
            return null;
        }
    }

    @Override
    public List<ReservationDto> getReservations() {
        return reservationRepository.findAll().stream().map(Reservation::getReservationDto).collect(Collectors.toList());
    }

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



