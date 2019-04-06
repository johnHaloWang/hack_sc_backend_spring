package com.example.demo.controller;

import com.example.demo.dto.LoginDTO;
import com.example.demo.dto.TokenDTO;
import com.example.demo.security.service.JsonWebTokenService;
import com.example.demo.security.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    private final TokenService tokenService;
    Logger logger = LogManager.getLogger(AuthenticationController.class);

    @Autowired
    public AuthenticationController(final JsonWebTokenService tokenService) {
        this.tokenService = tokenService;
    }
    

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> authenticate(@RequestBody final LoginDTO dto) {
        final String token = tokenService.getToken(dto.getUsername(), dto.getPassword());
        logger.info("token:  " + token);
        if (token != null) {
            final TokenDTO response = new TokenDTO();
            response.setToken(token);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Authentication failed", HttpStatus.BAD_REQUEST);
        }
    }
    
    
    
    
}

