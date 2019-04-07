package com.example.demo.converter.dto;

import com.example.demo.dto.ProductDTO;
import com.example.demo.dto.RegisterDTO;
import com.example.demo.model.Authority;
import com.example.demo.model.Product;
import com.example.demo.model.User;

import org.bson.types.ObjectId;
import org.springframework.core.convert.converter.Converter;

import java.util.ArrayList;
import java.util.List;

public class ProductDTOConverter implements Converter<ProductDTO, Product> {

	@Override
	public Product convert(ProductDTO dto) {
		final Product product = new Product();
		
		product.setAvailable(dto.isAvailable());
		product.setBrand(dto.getBrand());
		product.setCreationTime(dto.getCreationTime());
		product.setGasCost(dto.getGasCost());
		product.setGeolocation(dto.getGeolocation());
		product.setName(dto.getName());
		product.setPictureFileName(dto.getPictureFileName());
		product.setPrice(dto.getPrice());
		product.setStocked_date(dto.getStocked_date());
		product.setStore_id(dto.getStore_id());
		
		
		return product;
	}

}
