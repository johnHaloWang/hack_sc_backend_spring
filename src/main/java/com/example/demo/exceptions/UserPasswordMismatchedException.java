package com.example.demo.exceptions;

public class UserPasswordMismatchedException extends Exception{
	public UserPasswordMismatchedException(String msg) {
        super(msg);
    }
}
