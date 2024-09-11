package com.sprrestaurant.controllers;

import com.sprrestaurant.dtos.CategoryDto;
import com.sprrestaurant.dtos.ProductDto;
import com.sprrestaurant.dtos.ReservationDto;

import com.sprrestaurant.services.Customer.CustomerService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CustomerControllerTest {

    @Mock
    private CustomerService customerService;

    @InjectMocks
    private CustomerController customerController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllCategories() {
        CategoryDto category1 = new CategoryDto();
        category1.setName("Lunch");

        CategoryDto category2 = new CategoryDto();
        category2.setName("Dinner");

        List<CategoryDto> mockCategories = Arrays.asList(category1, category2);
        when(customerService.getAllCategories()).thenReturn(mockCategories);

        ResponseEntity<List<CategoryDto>> response = customerController.getAllCategories();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, response.getBody().size());
        assertEquals("Lunch", response.getBody().get(0).getName());
        assertEquals("Dinner", response.getBody().get(1).getName());
    }

    @Test
    void getAllCategoriesByName() {
        String name = "Lun";
        CategoryDto category = new CategoryDto();
        category.setName("Lunch");

        List<CategoryDto> mockCategories = Arrays.asList(category);
        when(customerService.getAllCategoriesByName(name)).thenReturn(mockCategories);

        ResponseEntity<List<CategoryDto>> response = customerController.getAllCategoriesByName(name);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
        assertEquals("Lunch", response.getBody().get(0).getName());
    }

    @Test
    void getProductsByCategory() {
        Long categoryId = 1L;
        ProductDto product1 = new ProductDto();
        product1.setName("Fried Rice");
        ProductDto product2 = new ProductDto();
        product2.setName("Biryani");

        List<ProductDto> mockProducts = Arrays.asList(product1, product2);
        when(customerService.getProductsByCategory(categoryId)).thenReturn(mockProducts);

        ResponseEntity<List<ProductDto>> response = customerController.getProductsByCategory(categoryId);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, response.getBody().size());
        assertEquals("Fried Rice", response.getBody().get(0).getName());
        assertEquals("Biryani", response.getBody().get(1).getName());
    }

    @Test
    void getProductsByCategoryAndTitle() {
        Long categoryId = 1L;
        String title = "Fri";
        ProductDto product = new ProductDto();
        product.setName("Fried Rice");

        List<ProductDto> mockProducts = Arrays.asList(product);
        when(customerService.getProductsByCategoryAndTitle(categoryId, title)).thenReturn(mockProducts);

        ResponseEntity<List<ProductDto>> response = customerController.getProductsByCategoryAndTitle(categoryId, title);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
        assertEquals("Fried Rice", response.getBody().get(0).getName());
    }

    @Test
    void postReservation() {
        // Create a ReservationDto object with test data
        ReservationDto reservationDto = new ReservationDto();
        reservationDto.setCustomerName("Sachintha");


        when(customerService.postReservation(any(ReservationDto.class))).thenReturn(reservationDto);

        ResponseEntity<?> response = customerController.postReservation(reservationDto);

        // Verify the response
        assertNotNull(response);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertTrue(response.getBody() instanceof ReservationDto);
        ReservationDto responseBody = (ReservationDto) response.getBody();
        assertEquals("Sachintha", responseBody.getCustomerName());
    }

    @Test
    void getReservationsByUser() {
        Long userId = 1L;
        ReservationDto reservation1 = new ReservationDto();
        reservation1.setCustomerName("Sachintha");
        ReservationDto reservation2 = new ReservationDto();
        reservation2.setCustomerName("Pathum");

        List<ReservationDto> mockReservations = Arrays.asList(reservation1, reservation2);
        when(customerService.getReservationsByUser(userId)).thenReturn(mockReservations);

        ResponseEntity<List<ReservationDto>> response = customerController.getReservationsByUser(userId);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, response.getBody().size());
        assertEquals("Sachintha", response.getBody().get(0).getCustomerName());
        assertEquals("Pathum", response.getBody().get(1).getCustomerName());
    }
}