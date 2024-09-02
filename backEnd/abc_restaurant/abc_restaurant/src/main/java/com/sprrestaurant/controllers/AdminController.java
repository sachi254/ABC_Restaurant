package com.sprrestaurant.controllers;

import com.sprrestaurant.dtos.CategoryDto;
import com.sprrestaurant.dtos.ProductDto;
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



}
