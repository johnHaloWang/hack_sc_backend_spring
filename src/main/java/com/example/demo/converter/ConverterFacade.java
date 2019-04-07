package com.example.demo.converter;

import com.example.demo.controller.AuthenticationController;
import com.example.demo.converter.factory.ConverterFactory;
import com.example.demo.dto.UserDTO;
import com.example.demo.dto.RegisterDTO;
import com.example.demo.dto.StoreDTO;
import com.example.demo.model.User;
import com.example.demo.model.Store;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

@Component
public class ConverterFacade {

    @Autowired
    private ConverterFactory converterFactory;
    
    Logger logger = LogManager.getLogger(AuthenticationController.class);
    @SuppressWarnings("unchecked")
    public User convertUserDTO(final UserDTO dto) {
        return (User) converterFactory.getUserConverter(dto.getClass()).convert(dto);
    }
    @SuppressWarnings("unchecked")
    public User convertRegisterDTO(final RegisterDTO dto) {
        return (User) converterFactory.getRegisterConverter(dto.getClass()).convert(dto);
    }
    @SuppressWarnings("unchecked")
	public Store convertStoreDTO(final StoreDTO dto) {
        return (Store) converterFactory.getStoreConverter(dto.getClass()).convert(dto);
    }
}
