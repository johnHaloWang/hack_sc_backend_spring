package com.example.demo.exceptions;

public class UserDoesntExistedException extends Exception{
	public UserDoesntExistedException(String msg) {
        super(msg);
    }
}
