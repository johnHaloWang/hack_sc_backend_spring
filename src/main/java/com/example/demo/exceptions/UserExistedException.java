package com.example.demo.exceptions;


public class UserExistedException extends Exception {
	public UserExistedException(String msg) {
        super(msg);
    }
}