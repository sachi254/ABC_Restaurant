package com.sprrestaurant.services.admin;

import com.sprrestaurant.dtos.CategoryDto;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public interface AdminService {


    CategoryDto postCategory(CategoryDto categoryDto) throws IOException;
}
