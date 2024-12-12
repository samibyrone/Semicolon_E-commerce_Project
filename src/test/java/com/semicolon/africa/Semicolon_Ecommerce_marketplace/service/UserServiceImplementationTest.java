package com.semicolon.africa.Semicolon_Ecommerce_marketplace.service;

import com.semicolon.africa.Semicolon_Ecommerce_marketplace.data.repositories.UserRepository;
import com.semicolon.africa.Semicolon_Ecommerce_marketplace.dtos.requests.UserRegisterRequest;
import com.semicolon.africa.Semicolon_Ecommerce_marketplace.dtos.responses.UserRegisterResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
//@ActiveProfiles("test")
public class UserServiceImplementationTest {

    @Autowired
    private UserServiceImplementation userServiceImplementation;

    @Autowired
    UserRepository userRepository;

    @BeforeEach
    public void setUp() {
        userRepository.deleteAll();
    }

    @Test
    public void testThatUserCanBeRegistered() {
        UserRegisterResponse response = registerUser();
        assertThat(response).isNotNull();
        assertThat(userServiceImplementation.getAllUsers().size()).isEqualTo(1L);
        assertThat(response.getMessage()).contains("Registered Successfully");
    }

    private UserRegisterResponse registerUser() {
        UserRegisterRequest request = new UserRegisterRequest();
        request.setFirstName("samson");
        request.setLastName("ibironke");
        request.setUserName("Sammy");
        request.setEmail("ibironke@gmail.com");
        request.setPassword("12345");
        request.setPhoneNumber("08187606899");
        request.setRole("CUSTOMER");
        request.setAddress("302, alagomeji bus-stop sabo yaba Lagos");
        UserRegisterResponse response = userServiceImplementation.registerUser(request);
        return response;
    }
}