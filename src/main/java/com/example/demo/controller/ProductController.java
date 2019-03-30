package com.example.demo.controller;

//import java.io.FileWriter;
//import java.io.IOException;
//import java.io.Writer;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.HashMap;
import java.util.List;
//import java.util.Map;
//import java.util.Set;
//import java.net.InetAddress;

import com.example.demo.DemoApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

//import com.example.demo.DemoApplication;
import com.example.demo.data.Geolocation;
import com.example.demo.data.Product;
//import com.example.demo.data.provider.StoreManager;
import com.example.demo.data.Store;
import com.example.demo.data.repository.StoreInventoryRepository;
import com.example.demo.data.provider.ProductManager;
import org.bson.types.ObjectId;

@RestController
@RequestMapping("/product")
public class ProductController {
	@Autowired
	private StoreInventoryRepository storeInventoryRepository;
	@Autowired
	private ProductManager productManager;

	@RequestMapping(value = "/{productId}", method = RequestMethod.GET)
	Product getStore(@PathVariable("productId") ObjectId id) {
		// json file database method
		// Store store = storeManager.getStore(storeId);

		// using MongoRepository to handle it
		Product product = storeInventoryRepository.findBy_id(id);
		return product;
	}

	@RequestMapping(value = "/{productId}", method = RequestMethod.POST)
	Product updateStore(@PathVariable("productId") ObjectId id, 
			@RequestParam("name") String name,
			@RequestParam("pictureFileName") String pictureFileName, 
			@RequestParam("brand") String brand,
			@RequestParam("price") double price, 
			@RequestParam("ava") boolean ava,
			@RequestParam("dateStocked") String stocked_date, 
			@RequestParam("storeId") String store_id,
			@RequestParam("latitude") float latitude,
			@RequestParam("longitude") float longitude){
		
		Product p = new Product();
		p.setName(name);
		p.set_id(id);
		p.setBrand(brand);
		p.setName(name);
		p.setPictureFileName(pictureFileName);
		Geolocation loc = new Geolocation();
		loc.setLatitude(latitude);
		loc.setLongitude(longitude);
		p.setGeolocation(loc);
		p.setStore_id(store_id);
		p.setPrice(price);
		p.setStocked_date(stocked_date);
		p.setAvailable(ava);
		this.storeInventoryRepository.save(p);
		return p;
	}

	@RequestMapping(value = "/{productId}", method = RequestMethod.DELETE)
	void deleteStore(@PathVariable("productId") ObjectId id) {
		// using MongoRepository to handle it
		storeInventoryRepository.delete(storeInventoryRepository.findBy_id(id));
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	List<Product> listAllProducts() {
		return storeInventoryRepository.findAll();
	}
	
	
	@RequestMapping(value = "/listGeo/{name}", method = RequestMethod.GET)
	List<Product> listNameByRadius(
		@RequestParam("name") String name,
		@RequestParam("latitude") float latitude,
		@RequestParam("longitude") float longitude,
		@RequestParam("radius") float radius){
		Geolocation geo = new Geolocation();
		geo.setLatitude(latitude);
		geo.setLongitude(longitude);
		return productManager.getProductRadius(name, geo, radius);
	}
	
	  @RequestMapping(value = "/add/{name}", method = RequestMethod.PUT)
	   Product addStore(
	           @RequestParam("name") String name,
				@RequestParam("pictureFileName") String pictureFileName, 
				@RequestParam("brand") String brand,
				@RequestParam("price") double price, 
				@RequestParam("ava") boolean ava,
				@RequestParam("dateStocked") String stocked_date, 
				@RequestParam("storeId") String store_id,
				@RequestParam("latitude") float latitude,
				@RequestParam("longitude") float longitude){
		     
	        Product p = new Product();
			p.setName(name);
			p.set_id(ObjectId.get());
			p.setBrand(brand);
			p.setName(name);
			p.setPictureFileName(pictureFileName);
			Geolocation loc = new Geolocation();
			loc.setLatitude(latitude);
			loc.setLongitude(longitude);
			p.setGeolocation(loc);
			p.setStore_id(store_id);
			p.setPrice(price);
			p.setStocked_date(stocked_date);
			p.setAvailable(ava);
			 this.storeInventoryRepository.insert(p);
	       return p;
	   }
}
