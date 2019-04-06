package com.example.demo.controller;

import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.bind.annotation.RestController;
import com.example.demo.data.Geolocation;
import com.example.demo.data.Product;
import com.example.demo.data.provider.ProductManager;
import com.example.demo.exceptions.ProductDuplicateItemException;

import org.bson.types.ObjectId;



/**
 * @author johnhalowang, Lisa Chen
 *
 */
@RestController
@RequestMapping("/api/product")
public class ProductController {

	@Autowired
	private ProductManager productManager;

	@RequestMapping(value = "/{productId}", method = RequestMethod.GET)
	Product getProduct(@PathVariable("productId") ObjectId id) {
		
		// using MongoRepository to handle it
		Product product = productManager.findProductById(id);
		return product;
	}

	@RequestMapping(value = "/{productId}", method = RequestMethod.POST)
	Product updateProduct(@PathVariable("productId") ObjectId id, 
			@RequestParam("name") String name,
			@RequestParam("pictureFileName") String pictureFileName, 
			@RequestParam("brand") String brand,
			@RequestParam("price") double price, 
			@RequestParam("available,") boolean available,
			@RequestParam("dateStocked") String stocked_date, 
			@RequestParam("storeId") String store_id,
			@RequestParam("latitude") float latitude,
			@RequestParam("longitude") float longitude) throws ProductDuplicateItemException{
		
		Geolocation loc = new Geolocation();
		loc.setLatitude(latitude);
		loc.setLongitude(longitude);		
		Product p = new Product(id, store_id, price, name, brand, available, stocked_date, pictureFileName, loc);
		productManager.updateProduct(p);
		return p;
	}

	@RequestMapping(value = "/{productId}", method = RequestMethod.DELETE)
	void deleteProduct(@PathVariable("productId") ObjectId id) {
		
		productManager.deleteProduct(id);
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	List<Product> listAllProducts() {
		
		return productManager.listAllProducts();
	}
	
	
	@RequestMapping(value = "/listGeo/{name}", method = RequestMethod.GET)
	List<Product> listNameByRadius(
			@RequestParam("name") String name,
			@RequestParam("latitude") float latitude,
			@RequestParam("longitude") float longitude,
			@RequestParam("radius") float radius,
			@RequestParam("mpg") float mpg) throws IOException{
		
		Geolocation geo = new Geolocation();
		geo.setLatitude(latitude);
		geo.setLongitude(longitude);
		return productManager.getProductsInRadius(name, geo, radius, mpg);
	}
	
	  @RequestMapping(value = "/add/{name}", method = RequestMethod.PUT)
	   Product addProduct(		   
			@RequestParam("name") String name,
			@RequestParam("pictureFileName") String pictureFileName, 
			@RequestParam("brand") String brand,
			@RequestParam("price") double price, 
			@RequestParam("available") boolean available,
			@RequestParam("dateStocked") String stocked_date, 
			@RequestParam("storeId") String store_id,
			@RequestParam("latitude") float latitude,
			@RequestParam("longitude") float longitude) throws ProductDuplicateItemException{
		     

			Geolocation loc = new Geolocation();
			loc.setLatitude(latitude);
			loc.setLongitude(longitude);
	        Product p = new Product(ObjectId.get(), store_id, price, name, brand, available, stocked_date, pictureFileName, loc);
			productManager.addProduct(p);
	        return p;
	   }
}
