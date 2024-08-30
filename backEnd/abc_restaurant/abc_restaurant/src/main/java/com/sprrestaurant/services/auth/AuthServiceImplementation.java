package com.sprrestaurant.services.auth;

import com.sprrestaurant.dtos.SignupRequest;
import com.sprrestaurant.dtos.UserDto;
import com.sprrestaurant.entities.User;
import com.sprrestaurant.enums.UserRole;
import com.sprrestaurant.repositories.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImplementation implements AuthService{

    private final UserRepository userRepository;



    public AuthServiceImplementation(UserRepository userRepository) {
        this.userRepository = userRepository;

    }

    @PostConstruct
    public void createAdminAccount(){
        User adminAccount = userRepository.findByUserRole(UserRole.ADMIN);
        if(adminAccount == null){
            User user = new User();
            user.setName("Admin");
            user.setEmail("admin@outlook.com");
            user.setPassword(new BCryptPasswordEncoder().encode("123"));
            user.setUserRole(UserRole.ADMIN);
            userRepository.save(user);
        }
    }




    @Override
    public UserDto createUser(SignupRequest signupRequest) {
        User user = new User();
        user.setName(signupRequest.getName());
        user.setEmail(signupRequest.getEmail());
        user.setPassword(new BCryptPasswordEncoder().encode(signupRequest.getPassword()));
        user.setUserRole(UserRole.CUSTOMER);
        User createUser = userRepository.save(user);
        UserDto createUserDto = new UserDto();
        createUserDto.setId(createUser.getId());
        createUserDto.setName(createUser.getName());
        createUserDto.setEmail(createUser.getEmail());
        createUserDto.setUserRole(createUser.getUserRole());
        return createUserDto;
    }
}
