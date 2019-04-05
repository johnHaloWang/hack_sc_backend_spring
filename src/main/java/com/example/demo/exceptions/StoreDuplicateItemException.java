package com.example.demo.exceptions;

public class StoreDuplicateItemException extends Exception{
	public StoreDuplicateItemException(String msg) {
        super(msg);
    }
}
