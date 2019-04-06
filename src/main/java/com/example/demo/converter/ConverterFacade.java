package com.example.demo.converter;

import com.example.demo.converter.factory.ConverterFactory;
import com.example.demo.dto.UserDTO;
import com.example.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class ConverterFacade {

    @Autowired
    private ConverterFactory converterFactory;

    public User convert(final UserDTO dto) {
        return (User) converterFactory.getConverter(dto.getClass()).convert(dto);
    }
}
