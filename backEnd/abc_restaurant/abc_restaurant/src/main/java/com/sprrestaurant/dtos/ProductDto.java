package com.sprrestaurant.dtos;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class ProductDto {

//productDto

    private Long id;

    private  String name;

    private  String price;

    private  String description;

    private byte[] returnedImg;

    private MultipartFile img;

    private String categoryName;

    private Long categoryId;

}
