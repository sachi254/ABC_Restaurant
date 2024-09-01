package com.sprrestaurant.services.admin;


import com.sprrestaurant.dtos.CategoryDto;
import com.sprrestaurant.entities.Category;
import com.sprrestaurant.repositories.CategoryRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService{


  private final CategoryRepository categoryRepository;




    @Override
    public CategoryDto postCategory(CategoryDto categoryDto) throws IOException {

        Category category = new Category();
        category.setName(categoryDto.getName());
        category.setDescription(categoryDto.getDescription());
        category.setImg(categoryDto.getImg().getBytes());
        Category createdCategory  = categoryRepository.save(category);
        CategoryDto createdCategoryDto  =  new CategoryDto();
        createdCategoryDto.setId(createdCategory.getId());
        return createdCategoryDto;
    }
}
