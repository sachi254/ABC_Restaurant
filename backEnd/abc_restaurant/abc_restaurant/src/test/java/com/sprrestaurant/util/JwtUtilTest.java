package com.sprrestaurant.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.User;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

class JwtUtilTest {

    private JwtUtil jwtUtil;
    private UserDetails userDetails;
    private String token;


    @BeforeEach
    void setUp() {
        jwtUtil = new JwtUtil();

        // Mocking UserDetails
        userDetails = new User("sachintha@gmail.com", "Sachi@2024", new ArrayList<>());
        // Generate Token
        token = jwtUtil.generateToken(userDetails);
    }

    @Test
    void generateToken() {
        assertNotNull(token);
    }

    @Test
    void extractUsername() {
        String username = jwtUtil.extractUsername(token);
        assertEquals("sachintha@gmail.com", username);
    }

    @Test
    void isTokenValid() {
        boolean isValid = jwtUtil.isTokenValid(token, userDetails);
        assertTrue(isValid);
    }


}