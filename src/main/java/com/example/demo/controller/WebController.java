package com.example.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebController {
      
    
	@RequestMapping(value = "/server/ping", method = RequestMethod.GET)
    String healthCheck() {
        return "OK";
    }
	@RequestMapping(value = "/server/hello", method = RequestMethod.GET)
    String getGreeting()
    {
       return "Hello, world!";
    }
	
  
    
    
}
