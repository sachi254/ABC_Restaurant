package com.sprrestaurant.controllers;


import com.sprrestaurant.dtos.SignupRequest;
import com.sprrestaurant.dtos.UserDto;
import com.sprrestaurant.services.auth.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")

public class AuthController {

    public final AuthService authService;

    public AuthController(AuthService authService){
        this.authService = authService;
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signupUser(@RequestBody SignupRequest signupRequest){
            UserDto createUserDto = authService.createUser(signupRequest);

            if(createUserDto == null){

                return new ResponseEntity<>("User not created. Come again later", HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(createUserDto, HttpStatus.CREATED);

    }

}
