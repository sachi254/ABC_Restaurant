package com.sprrestaurant.controllers;


import com.sprrestaurant.dtos.AuthenticationRequest;
import com.sprrestaurant.dtos.AuthenticationResponse;
import com.sprrestaurant.dtos.SignupRequest;
import com.sprrestaurant.dtos.UserDto;
import com.sprrestaurant.repositories.UserRepository;
import com.sprrestaurant.services.auth.AuthService;
import com.sprrestaurant.services.jwt.UserService;
import com.sprrestaurant.util.JwtUtil;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AuthControllerTest {

    @InjectMocks
    private AuthController authController;

    @Mock
    private AuthService authService;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private UserService userService;

    @Mock
    private UserDetailsService userDetailsService;

    @Mock
    private JwtUtil jwtUtil;

    @Mock
    private HttpServletResponse response;

    @Mock
    private UserRepository userRepository; // Add UserRepository mock

    private SignupRequest signupRequest;
    private AuthenticationRequest authenticationRequest;
    private UserDetails userDetails;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        // Sample signup request
        signupRequest = new SignupRequest();
        signupRequest.setEmail("sachintha@gmail.com");
        signupRequest.setPassword("Sachi@2024");

        authenticationRequest = new AuthenticationRequest();
        authenticationRequest.setEmail("sachintha@gmail.com");
        authenticationRequest.setPassword("Sachi@2024");

        userDetails = new User("sachintha@gmail.com", "Sachi@2024", new ArrayList<>()); // Mock user details

        when(userService.userDetailsService()).thenReturn(userDetailsService);
        when(userDetailsService.loadUserByUsername(authenticationRequest.getEmail())).thenReturn(userDetails);
        when(userRepository.findFirstByEmail(authenticationRequest.getEmail()))
                .thenReturn(java.util.Optional.of(new com.sprrestaurant.entities.User())); // Mock User
    }

    @Test
    void signupUser() {

        when(authService.createUser(signupRequest)).thenReturn(new UserDto());
        ResponseEntity<?> response = authController.signupUser(signupRequest);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    void createAuthenticationToken_Success() throws IOException {
        // Mocking authentication and token generation
        when(authenticationManager.authenticate(any())).thenReturn(null);
        when(jwtUtil.generateToken(userDetails)).thenReturn("testtoken");

        // Testing successful token creation
        AuthenticationResponse authResponse = authController.createAuthenticationToken(authenticationRequest, response);
        assertNotNull(authResponse);
        assertEquals("testtoken", authResponse.getJwt());
    }


    @Test
    void createAuthenticationToken_BadCredentials() {
        // Simulate bad credentials exception
        doThrow(new BadCredentialsException("Incorrect username or password"))
                .when(authenticationManager).authenticate(any());

        BadCredentialsException exception = assertThrows(BadCredentialsException.class, () ->
                authController.createAuthenticationToken(authenticationRequest, response));
        assertEquals("Incorrect username or password", exception.getMessage());
    }


    @Test
    void createAuthenticationToken_UserDisabled() throws IOException {
        // Simulate user being disabled
        doThrow(new DisabledException("User not active")).when(authenticationManager).authenticate(any());

        authController.createAuthenticationToken(authenticationRequest, response);

        verify(response).sendError(HttpServletResponse.SC_NOT_FOUND, "User not active");
    }
}