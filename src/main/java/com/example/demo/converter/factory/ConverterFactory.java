package com.example.demo.converter.factory;

import com.example.demo.converter.dto.*;

import com.example.demo.dto.*;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;


@Component
public class ConverterFactory {

    @SuppressWarnings("rawtypes")
	private Map<Object, Converter> userConverters;
    @SuppressWarnings("rawtypes")
	private Map<Object, Converter> registerConverters;
    @SuppressWarnings("rawtypes")
	private Map<Object, Converter> storeConverters;
    
    public ConverterFactory() {

    }

    @PostConstruct
    public void init() {
    	userConverters = new HashMap<>();
    	registerConverters = new HashMap<>();
    	storeConverters = new HashMap<>();
    	userConverters.put(UserDTO.class, new UserDTOConverter());
    	registerConverters.put(RegisterDTO.class, new RegisterDTOConverter());
    	storeConverters.put(StoreDTO.class, new StoreDTOConverter());
        
    }

    @SuppressWarnings("rawtypes")
	public Converter getUserConverter(final Object type) {
        return userConverters.get(type);
    }
    @SuppressWarnings("rawtypes")
	public Converter getRegisterConverter(final Object type) {
        return registerConverters.get(type);
    }
    @SuppressWarnings("rawtypes")
	public Converter getStoreConverter(final Object type) {
        return storeConverters.get(type);
    }
}