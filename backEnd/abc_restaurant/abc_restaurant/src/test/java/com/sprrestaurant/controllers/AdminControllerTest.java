package com.sprrestaurant.controllers;

import com.sprrestaurant.dtos.ProductDto;
import com.sprrestaurant.dtos.ReservationDto;
import com.sprrestaurant.enums.ReservationStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.junit.jupiter.api.Assertions.*;
import com.sprrestaurant.dtos.CategoryDto;
import com.sprrestaurant.services.admin.AdminService;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
class AdminControllerTest {


    @Mock
    private AdminService adminService;

    @InjectMocks
    private AdminController adminController;



    @Test
    void postProduct() throws IOException {
        Long categoryId = 1L;
        ProductDto productDto = new ProductDto();
        productDto.setName("Fried Rice");

        when(adminService.postProduct(eq(categoryId), any(ProductDto.class))).thenReturn(productDto);
        ResponseEntity<?> response = adminController.postProduct(categoryId, productDto);

        assertNotNull(response);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());

        assertTrue(response.getBody() instanceof ProductDto);
        ProductDto responseBody = (ProductDto) response.getBody();
        assertEquals("Fried Rice", responseBody.getName());
    }




    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testPostCategory() throws Exception {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setName("Lunch");

        when(adminService.postCategory(any(CategoryDto.class))).thenReturn(categoryDto);

        ResponseEntity<CategoryDto> response = adminController.postCategory(categoryDto);
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Lunch", response.getBody().getName());
    }

    @Test
    void testGetAllCategories() {
        CategoryDto category1 = new CategoryDto();
        category1.setName("Lunch");

        CategoryDto category2 = new CategoryDto();
        category2.setName("Snacks");

        List<CategoryDto> mockCategories = Arrays.asList(category1, category2);
        when(adminService.getAllCategories()).thenReturn(mockCategories);

        ResponseEntity<List<CategoryDto>> response = adminController.getAllCategories();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, response.getBody().size());
    }


    @Test
    void getAllCategoriesByTitle() {
        String title = "Lun";
        CategoryDto category = new CategoryDto();
        category.setName("Lunch");

        List<CategoryDto> mockCategories = Arrays.asList(category);
        when(adminService.getAllCategoriesByTitle(eq(title))).thenReturn(mockCategories);

        ResponseEntity<List<CategoryDto>> response = adminController.getAllCategoriesByTitle(title);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
        assertEquals("Lunch", response.getBody().get(0).getName());
    }





    @Test
    void getAllProductsByCategory() {
        Long categoryId = 1L;
        ProductDto product1 = new ProductDto();
        product1.setName("Fried Rice");
        ProductDto product2 = new ProductDto();
        product2.setName("Biryani");

        List<ProductDto> mockProducts = Arrays.asList(product1, product2);
        when(adminService.getAllProductsByCategory(eq(categoryId))).thenReturn(mockProducts);

        ResponseEntity<List<ProductDto>> response = adminController.getAllProductsByCategory(categoryId);
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
        when(adminService.getProductsByCategoryAndTitle(eq(categoryId), eq(title))).thenReturn(mockProducts);

        ResponseEntity<List<ProductDto>> response = adminController.getProductsByCategoryAndTitle(categoryId, title);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
        assertEquals("Fried Rice", response.getBody().get(0).getName());
    }

    @Test
    void deleteProduct() {
        long productId = 1L;

        doNothing().when(adminService).deleteProduct(productId);

        ResponseEntity<Void> response = adminController.deleteProduct(productId);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    void getProductById() {
        long productId = 1L;
        ProductDto productDto = new ProductDto();
        productDto.setName("Fried Rice");

        when(adminService.getProductById(productId)).thenReturn(productDto);

        ResponseEntity<ProductDto> response = adminController.getProductById(productId);
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Fried Rice", response.getBody().getName());
    }


    @Test
    void updateProduct() throws IOException {
        long productId = 1L;
        ProductDto productDto = new ProductDto();
        productDto.setName("Chicken Fried Rice");

        when(adminService.updateProduct(eq(productId), any(ProductDto.class))).thenReturn(productDto);

        ResponseEntity<?> response = adminController.updateProduct(productId, productDto);

        // Check that the response is not null and has the correct status code.
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());

        assertTrue(response.getBody() instanceof ProductDto);
        ProductDto responseBody = (ProductDto) response.getBody();
        assertEquals("Chicken Fried Rice", responseBody.getName());
    }






    @Test
    void getReservations() {
        ReservationDto reservation1 = new ReservationDto();
        reservation1.setCustomerName("Sachintha");
        ReservationDto reservation2 = new ReservationDto();
        reservation2.setCustomerName("Pathum");

        List<ReservationDto> mockReservations = Arrays.asList(reservation1, reservation2);
        when(adminService.getReservations()).thenReturn(mockReservations);

        ResponseEntity<List<ReservationDto>> response = adminController.getReservations();
        assertEquals(HttpStatus.OK, response.getStatusCode());
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

        when(adminService.changeReservationStatus(reservationId, status)).thenReturn(updatedReservation);

        ResponseEntity<ReservationDto> response = adminController.changeReservationStatus(reservationId, status);
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(ReservationStatus.APPROVED, response.getBody().getReservationStatus());
    }


}