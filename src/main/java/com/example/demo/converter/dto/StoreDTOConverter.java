package com.example.demo.converter.dto;

import com.example.demo.controller.AuthenticationController;
import com.example.demo.dto.StoreDTO;

import com.example.demo.model.Store;
import org.springframework.core.convert.converter.Converter;


import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class StoreDTOConverter implements Converter<StoreDTO, Store> {

	Logger logger = LogManager.getLogger(AuthenticationController.class);
    @Override
    public Store convert(final StoreDTO dto) {
    	
        final Store store = new Store();
        
        store.setName(dto.getName());
        store.setPictureFileName(dto.getPictureFileName());
        store.setGeolocation(dto.getGeolocation());
        store.setAddress(dto.getAddress());
        store.setZipcode(dto.getZipcode());
        store.setCity(dto.getCity());
        store.setState(dto.getState());
        return store;
    }

}
