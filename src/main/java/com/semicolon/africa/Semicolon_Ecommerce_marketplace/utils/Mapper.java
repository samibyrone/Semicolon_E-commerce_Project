package com.semicolon.africa.Semicolon_Ecommerce_marketplace.utils;

import com.semicolon.africa.Semicolon_Ecommerce_marketplace.data.model.Product;
import com.semicolon.africa.Semicolon_Ecommerce_marketplace.data.model.User;
import com.semicolon.africa.Semicolon_Ecommerce_marketplace.dtos.requests.ProductRegisterRequest;
import com.semicolon.africa.Semicolon_Ecommerce_marketplace.dtos.requests.UserRegisterRequest;
import com.semicolon.africa.Semicolon_Ecommerce_marketplace.dtos.responses.ProductRegisterResponse;
import com.semicolon.africa.Semicolon_Ecommerce_marketplace.dtos.responses.UserLoginResponse;
import com.semicolon.africa.Semicolon_Ecommerce_marketplace.dtos.responses.UserRegisterResponse;

public class Mapper {

    public static void map(UserRegisterRequest userRegisterRequest, User user) {
        user.setFirstName(userRegisterRequest.getFirstName());
        user.setLastName(userRegisterRequest.getLastName());
        user.setUserName(userRegisterRequest.getUserName());
        user.setEmail(userRegisterRequest.getEmail());
        user.setAddress(userRegisterRequest.getAddress());
        user.setPhoneNumber(userRegisterRequest.getPhoneNumber());
        user.setPassword(userRegisterRequest.getPassword());
//        user.setRole(userRegisterRequest.getRole());
    }

    public static UserRegisterResponse map(User user) {
        UserRegisterResponse userRegisterResponse = new UserRegisterResponse();
        userRegisterResponse.setMessage("Registered Successfully");
        user.setEmail(user.getEmail());
        return userRegisterResponse;
    }

    public static UserLoginResponse mapLogin(User user) {
        UserLoginResponse userLoginResponse = new UserLoginResponse();
        user.setEmail(user.getEmail());
        userLoginResponse.setMessage("Logged in Successfully");
        userLoginResponse.setLoginSuccessful(true);
        return userLoginResponse;
    }

    public static void mapProduct(ProductRegisterRequest productRegisterRequest, Product product) {
        product.setProductName(product.getProductName());
        product.setProductDescription(product.getProductDescription());
        product.setProductPrice(product.getProductPrice());
        product.setProductStock(product.getProductStock());
        }

    public static ProductRegisterResponse mapProduct(Product product) {
        ProductRegisterResponse productRegisterResponse = new ProductRegisterResponse();
        productRegisterResponse.setMessage("Product Successfully Created");
        productRegisterResponse.setProduct_id(product.getProduct_id());
        return productRegisterResponse;
    }
}
