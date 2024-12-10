package com.semicolon.africa.Semicolon_Ecommerce_marketplace.service;

import com.semicolon.africa.Semicolon_Ecommerce_marketplace.data.model.User;
import com.semicolon.africa.Semicolon_Ecommerce_marketplace.data.repositories.UserRepository;
import com.semicolon.africa.Semicolon_Ecommerce_marketplace.dtos.requests.UserLoginRequest;
import com.semicolon.africa.Semicolon_Ecommerce_marketplace.dtos.requests.UserRegisterRequest;
import com.semicolon.africa.Semicolon_Ecommerce_marketplace.dtos.responses.UserLoginResponse;
import com.semicolon.africa.Semicolon_Ecommerce_marketplace.dtos.responses.UserRegisterResponse;
import com.semicolon.africa.Semicolon_Ecommerce_marketplace.exception.EmailAlreadyExist;
import com.semicolon.africa.Semicolon_Ecommerce_marketplace.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.semicolon.africa.Semicolon_Ecommerce_marketplace.utils.Mapper.map;
import static com.semicolon.africa.Semicolon_Ecommerce_marketplace.utils.Mapper.mapLogin;

@Service
public class UserServiceImplementation implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserRegisterResponse registerUser(UserRegisterRequest userRegisterRequest) {
        validateExistingEmail(userRegisterRequest.getEmail());
        User user = new User();
        map(userRegisterRequest, user);
        userRepository.save(user);
        return map(user);
    }

    private void validateExistingEmail(String email) {
        boolean existByEmail = userRepository.existByEmail(email);
        if (existByEmail) {
            throw new EmailAlreadyExist("Email Already Exist");
        }
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public UserLoginResponse loginUser(UserLoginRequest userLogin) {
        User user = findByEmail(userLogin.getEmail());
        mapLogin(user);
        user.setLoggedIn(true);
        userRepository.save(user);
        return mapLogin(user);
    }

    private User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow( () -> new UserNotFoundException("User does not exist"));
    }
}
