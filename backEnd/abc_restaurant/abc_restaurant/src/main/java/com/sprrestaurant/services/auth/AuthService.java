package com.sprrestaurant.services.auth;

import com.sprrestaurant.dtos.SignupRequest;
import com.sprrestaurant.dtos.UserDto;


public interface AuthService {

    UserDto createUser(SignupRequest signupRequest);

}
