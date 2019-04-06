package com.example.demo.dto;

import java.io.Serializable;


public class LoginDTO implements Serializable {

    private static final long serialVersionUID = -4159366809929151486L;

    private String username;
    private String password;

    public LoginDTO() {
    }
    public LoginDTO(String username, String password) {
    	this.username = username;
    	this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(final String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }
}

