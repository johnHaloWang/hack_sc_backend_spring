package com.example.demo.controller;

/**
 * Exception for when a product name matches another name in the database and both 
 * belong to the same store ID. This occurs when the same store tries to add a 
 * product that already exists instead of trying to update the existing one.
 * @author Lisa Chen
 *
 */
public class DuplicateProductException extends RuntimeException {}
