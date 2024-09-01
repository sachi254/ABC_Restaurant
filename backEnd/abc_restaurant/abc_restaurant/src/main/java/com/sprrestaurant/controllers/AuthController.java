package com.sprrestaurant.controllers;


import com.sprrestaurant.dtos.AuthenticationRequest;
import com.sprrestaurant.dtos.AuthenticationResponse;
import com.sprrestaurant.dtos.SignupRequest;
import com.sprrestaurant.dtos.UserDto;
import com.sprrestaurant.entities.User;
import com.sprrestaurant.repositories.UserRepository;
import com.sprrestaurant.services.auth.AuthService;

import com.sprrestaurant.services.jwt.UserService;
import com.sprrestaurant.util.JwtUtil;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Optional;



@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;



    @PostMapping("/signup")
    public ResponseEntity<?> signupUser(@RequestBody SignupRequest signupRequest){
            UserDto createUserDto = authService.createUser(signupRequest);

            if(createUserDto == null){

                return new ResponseEntity<>("User not created. Come again later", HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(createUserDto, HttpStatus.CREATED);

    }

    @PostMapping("/login")
    public AuthenticationResponse createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest, HttpServletResponse response) throws IOException {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(),authenticationRequest.getPassword()));
        }catch (BadCredentialsException e){
            throw new BadCredentialsException("Incorrect username or password");
        }catch (DisabledException disabledException){
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "User not active");
            return null;
        }

        final UserDetails userDetails = userService.userDetailsService().loadUserByUsername(authenticationRequest.getEmail());
            final String jwt = jwtUtil.generateToken(userDetails);

            Optional<User> optionalUser =userRepository.findFirstByEmail(userDetails.getUsername());
            AuthenticationResponse authenticationResponse = new AuthenticationResponse();
            if (optionalUser.isPresent()){
                authenticationResponse.setJwt(jwt);
                authenticationResponse.setUserRole(optionalUser.get().getUserRole());
                authenticationResponse.setUserId(optionalUser.get().getId());
            }
            return authenticationResponse;
    }



}
