package com.sprrestaurant.controllers;

import com.sprrestaurant.dtos.CategoryDto;
import com.sprrestaurant.dtos.ProductDto;
import com.sprrestaurant.dtos.ReservationDto;
import com.sprrestaurant.services.admin.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {


private final AdminService adminService;



    @PostMapping("/category")
    public ResponseEntity<CategoryDto>postCategory(@ModelAttribute CategoryDto categoryDto) throws IOException {
        CategoryDto createdCategoryDto = adminService.postCategory(categoryDto);
        if(createdCategoryDto == null) return  ResponseEntity.notFound().build();
        return ResponseEntity.ok(createdCategoryDto);
    }


    @GetMapping("/categories")
    public ResponseEntity<List<CategoryDto>> getAllCategories(){
        List<CategoryDto> categoryDtoList =adminService.getAllCategories();

        if(categoryDtoList == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(categoryDtoList);
    }


    @GetMapping("/categories/{title}")
    public ResponseEntity<List<CategoryDto>> getAllCategoriesByTitle(@PathVariable String title){
        List<CategoryDto> categoryDtoList =adminService.getAllCategoriesByTitle(title);

        if(categoryDtoList == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(categoryDtoList);
    }

//Start the product adding part

    @PostMapping("/{categoryId}/product")
    public ResponseEntity<?>postProduct( @PathVariable Long categoryId, @ModelAttribute ProductDto productDto) throws IOException {
        ProductDto createdProductDto = adminService.postProduct(categoryId,productDto);
        if(createdProductDto == null)
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Something Went Wrong");
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProductDto);
    }


    @GetMapping("/{categoryId}/products")
    public ResponseEntity<List<ProductDto>> getAllProductsByCategory(@PathVariable Long categoryId){
        List<ProductDto> productDtoList =adminService.getAllProductsByCategory(categoryId);

        if(productDtoList == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(productDtoList);
    }




    @GetMapping("/{categoryId}/product/{title}")
    public ResponseEntity<List<ProductDto>> getProductsByCategoryAndTitle(@PathVariable Long categoryId, @PathVariable String title){
        List<ProductDto> productDtoList = adminService.getProductsByCategoryAndTitle(categoryId,title);

        if(productDtoList == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(productDtoList);
    }


    @DeleteMapping("/product/{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long productId){
        adminService.deleteProduct(productId);
        return ResponseEntity.noContent().build();

    }


    @GetMapping("/product/{productId}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable Long productId){
        ProductDto productDto = adminService.getProductById(productId);

        if(productDto == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(productDto);
    }


    @PutMapping("/product/{productId}")
    public ResponseEntity<?> updateProduct(@PathVariable Long productId, @ModelAttribute ProductDto productDto) throws IOException {
        ProductDto updatedProductDto = adminService.updateProduct(productId, productDto);
        if (updatedProductDto == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Update failed. Please check the data and try again.");
        }
        return ResponseEntity.ok(updatedProductDto);
    }




    //Get all reservations
    @GetMapping("/reservations")
    public ResponseEntity<List<ReservationDto>> getReservations(){
        List<ReservationDto> reservationDtoList =adminService.getReservations();

        if(reservationDtoList == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(reservationDtoList);
    }

    //update the reservation status

    @GetMapping("/reservation/{reservationId}/{status}")
    public ResponseEntity<ReservationDto> changeReservationStatus(@PathVariable Long reservationId, @PathVariable String status){
        ReservationDto UpdatedReservationDto =adminService.changeReservationStatus(reservationId, status);

        if(UpdatedReservationDto == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(UpdatedReservationDto);
    }






}
