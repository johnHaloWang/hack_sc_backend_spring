package com.example.demo.security.service;

public interface TokenService {

    String getToken(String username, String password);
}