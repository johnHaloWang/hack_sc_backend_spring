package com.example.demo.security.service;

import com.example.demo.controller.AuthenticationController;
import com.example.demo.exceptions.data.UserNotFoundException;
import com.example.demo.model.User;
import com.example.demo.model.UserAuthentication;
import com.example.demo.security.constants.SecurityConstants;
import com.example.demo.service.BasicUserService;
import com.example.demo.service.UserService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

@Service
public class JsonWebTokenAuthenticationService implements TokenAuthenticationService {

	
	Logger logger = LogManager.getLogger(AuthenticationController.class);
	
    @Value("${security.token.secret.key}")
    private String secretKey;

    private final UserService userService;

    @Autowired
    public JsonWebTokenAuthenticationService(final BasicUserService basicUserService) {
        this.userService = basicUserService;
    }

    @Override
    public Authentication authenticate(final HttpServletRequest request) {
        final String token = request.getHeader(
        		SecurityConstants.AUTH_HEADER_NAME);
        final Jws<Claims> tokenData = parseToken(token);
        if (tokenData != null) {
            User user = getUserFromToken(tokenData);
            if (user != null) {
                return new UserAuthentication(user);
            }
        }
        return null;
    }

    private Jws<Claims> parseToken(final String token) {
        if (token != null) {
            try {
                return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            } catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException
                    | SignatureException | IllegalArgumentException e) {
                return null;
            }
        }
        return null;
    }

    private User getUserFromToken(final Jws<Claims> tokenData) {
        try {
            return (User) userService.findByUsername(tokenData.getBody().get("username").toString());
        } catch (UsernameNotFoundException e) {
            throw new UserNotFoundException("User "
                    + tokenData.getBody().get("username").toString() + " not found");
        }
    }
}