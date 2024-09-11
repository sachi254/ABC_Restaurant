package com.sprrestaurant.controllers;

import com.sprrestaurant.dtos.CategoryDto;
import com.sprrestaurant.dtos.ProductDto;
import com.sprrestaurant.dtos.ReservationDto;
import com.sprrestaurant.enums.ReservationStatus;
import com.sprrestaurant.services.staff.StaffService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class StaffControllerTest {

    @Mock
    private StaffService staffService;

    @InjectMocks
    private StaffController staffController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllCategories() {
        // Arrange
        CategoryDto category1 = new CategoryDto();
        category1.setName("Lunch");

        CategoryDto category2 = new CategoryDto();
        category2.setName("Dinner");

        List<CategoryDto> mockCategories = Arrays.asList(category1, category2);
        when(staffService.getAllCategories()).thenReturn(mockCategories);


        ResponseEntity<List<CategoryDto>> response = staffController.getAllCategories();


        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(2, response.getBody().size());
        assertEquals("Lunch", response.getBody().get(0).getName());
        assertEquals("Dinner", response.getBody().get(1).getName());
    }

    @Test
    void getAllCategoriesByName() {

        String title = "Lun";
        CategoryDto category = new CategoryDto();
        category.setName("Lunch");

        List<CategoryDto> mockCategories = Arrays.asList(category);
        when(staffService.getAllCategoriesByName(title)).thenReturn(mockCategories);


        ResponseEntity<List<CategoryDto>> response = staffController.getAllCategoriesByName(title);


        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
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
        when(staffService.getProductsByCategory(categoryId)).thenReturn(mockProducts);


        ResponseEntity<List<ProductDto>> response = staffController.getProductsByCategory(categoryId);


        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(2, response.getBody().size());
        assertEquals("Fried Rice", response.getBody().get(0).getName());
        assertEquals("Biryani", response.getBody().get(1).getName());
    }

    @Test
    void getProductsByCategoryAndTitle() {

        Long categoryId = 1L;
        String title = "Lunch";
        ProductDto product = new ProductDto();
        product.setName("Lunch");

        List<ProductDto> mockProducts = Arrays.asList(product);
        when(staffService.getProductsByCategoryAndTitle(categoryId, title)).thenReturn(mockProducts);


        ResponseEntity<List<ProductDto>> response = staffController.getProductsByCategoryAndTitle(categoryId, title);


        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().size());
        assertEquals("Lunch", response.getBody().get(0).getName());
    }

    @Test
    void getReservations() {

        ReservationDto reservation1 = new ReservationDto();
        reservation1.setCustomerName("Sachintha");
        ReservationDto reservation2 = new ReservationDto();
        reservation2.setCustomerName("Pathum");

        List<ReservationDto> mockReservations = Arrays.asList(reservation1, reservation2);
        when(staffService.getReservations()).thenReturn(mockReservations);


        ResponseEntity<List<ReservationDto>> response = staffController.getReservations();


        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(2, response.getBody().size());
        assertEquals("Sachintha", response.getBody().get(0).getCustomerName());
        assertEquals("Pathum", response.getBody().get(1).getCustomerName());
    }

    @Test
    void changeReservationStatus() throws IOException {
        long reservationId = 1L;
        String status = "Approved";
        ReservationDto updatedReservation = new ReservationDto();
        updatedReservation.setReservationStatus(ReservationStatus.APPROVED);

        when(staffService.changeReservationStatus(reservationId, status)).thenReturn(updatedReservation);

        ResponseEntity<ReservationDto> response = staffController.changeReservationStatus(reservationId, status);
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(ReservationStatus.APPROVED, response.getBody().getReservationStatus());
    }

}