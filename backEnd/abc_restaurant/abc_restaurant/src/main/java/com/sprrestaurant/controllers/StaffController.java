package com.sprrestaurant.controllers;


import com.sprrestaurant.dtos.CategoryDto;
import com.sprrestaurant.dtos.ProductDto;
import com.sprrestaurant.services.staff.StaffService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/staff")
@RequiredArgsConstructor
public class StaffController {

    private final StaffService staffService;



    @GetMapping("/categories")
    public ResponseEntity<List<CategoryDto>> getAllCategories(){
        List<CategoryDto> categoryDtoList =staffService.getAllCategories();

        if(categoryDtoList == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(categoryDtoList);
    }

    //Search categories by name
    @GetMapping("/categories/{title}")
    public ResponseEntity<List<CategoryDto>> getAllCategoriesByName(@PathVariable String title){
        List<CategoryDto> categoryDtoList =staffService.getAllCategoriesByName(title);

        if(categoryDtoList == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(categoryDtoList);
    }


    //Operation start

    //Get Categories to the dashboard
    @GetMapping("/{categoryId}/products")
    public ResponseEntity<List<ProductDto>> getProductsByCategory(@PathVariable Long categoryId){
        List<ProductDto> productDtoList =staffService.getProductsByCategory(categoryId);

        if(productDtoList == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(productDtoList);
    }



    // search products By Name (hear we get the category id and the name of the product)
    @GetMapping("/{categoryId}/product/{title}")
    public ResponseEntity<List<ProductDto>> getProductsByCategoryAndTitle(@PathVariable Long categoryId, @PathVariable String title){
        List<ProductDto> productDtoList = staffService.getProductsByCategoryAndTitle(categoryId,title);

        if(productDtoList == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(productDtoList);
    }


}
