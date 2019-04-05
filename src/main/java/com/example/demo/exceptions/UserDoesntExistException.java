package com.example.demo.exceptions;

public class UserDoesntExistException extends Exception{
	public UserDoesntExistException(String msg) {
        super(msg);
    }
}
