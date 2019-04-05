package com.example.demo.data.provider.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.example.demo.data.AverageGasPriceCalculator;
import com.example.demo.data.Geolocation;
import com.example.demo.data.Product;
import com.example.demo.data.provider.ProductManager;
import com.example.demo.data.repository.StoreInventoryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;



/**
 * @author johnhalowang, Lisa Chen, Gina 
 *
 */

public class FS_ProductManager implements ProductManager{
	
	@Autowired
	private StoreInventoryRepository storeInventoryRepository;

	@Override
	public List<Product> getProductsInRadius(String productName, Geolocation geo, 
			double miles, double mpg) throws IOException {
		List<Product> list = new ArrayList<Product>();
		Collection<Product> collection = getProductByName(productName);
		double pricePerGallon = new AverageGasPriceCalculator(geo).getAveragePrice();
		double pricePerMiles = pricePerGallon / mpg;
		for (Product product: collection) {
			double productDistance = calcGPSDistance(product.getGeolocation(), geo);
			if(miles> productDistance)
			{
				double gasCost = pricePerMiles * productDistance;
				gasCost = 0.01 * Math.round(gasCost * 100);
				product.setGasCost(gasCost);
				list.add(product);
			}
		}
		return list;
	}

	@Override
	public Collection<Product>getProductByName(String name) {
		return storeInventoryRepository.findByNameLike(name);
	}
	
	private double calcGPSDistance(Geolocation geo1, Geolocation geo2) {
		final int EARTH_RADIUS = 6371; // Radius of the earth
		final int MILLIMETERS_IN_METER = 1000;
		final double METERS_IN_ONE_MILE = 1609.34;
		double latitude1 = geo1.getLatitude();
		double latitude2 = geo2.getLatitude();
		double longitude1 = geo1.getLongitude();
		double longitude2 = geo2.getLongitude();
	    double latDistance = Math.toRadians(latitude2 - latitude1);
	    double longDistance = Math.toRadians(longitude2 - longitude1);
	    //calculates distance using radians
	    double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
	            + Math.cos(Math.toRadians(latitude1)) * Math.cos(Math.toRadians(latitude2))
	            * Math.sin(longDistance / 2) * Math.sin(longDistance / 2);
	    double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

	    return EARTH_RADIUS * c * MILLIMETERS_IN_METER / METERS_IN_ONE_MILE; //convert mm to mile
	}
	
	@Override
	public void updateProduct(Product product) {
		storeInventoryRepository.save(product);
	}
	
	@Override
	public void deleteProduct(ObjectId id) {
		storeInventoryRepository.delete(storeInventoryRepository.findBy_id(id));
	}
	
	@Override
	public List<Product> listAllProducts() {
		return storeInventoryRepository.findAll();
	}
	@Override
	public void addProduct(Product product) {
		storeInventoryRepository.insert(product);
	}
	
	@Override
	public Product findProductById(ObjectId id) {
		return storeInventoryRepository.findBy_id(id);
	}
}
