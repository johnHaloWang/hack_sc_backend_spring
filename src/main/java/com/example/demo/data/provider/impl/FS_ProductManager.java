package com.example.demo.data.provider.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.example.demo.exceptions.ProductDuplicateItemException;
import com.example.demo.model.AverageGasPriceCalculator;
import com.example.demo.model.Geolocation;
import com.example.demo.model.Product;
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
	public List<Product>getAvailableProductsInRadius(String productName, Geolocation geo, 
			double miles, double mpg) throws IOException {
		List<Product> allMatchingProducts = getProductsInRadius(productName, geo, miles, mpg);
		for (Product product: allMatchingProducts)
		{
			if (!product.isAvailable())
				allMatchingProducts.remove(product);
		}
		return allMatchingProducts;
	}
	
	@Override
	public Collection<Product> getProductByName(String name) {
		return storeInventoryRepository.findByNameLike(name);
	}
	
	private double calcGPSDistance(Geolocation geo1, Geolocation geo2) {
		//TODO Improvement through GPS routes with distance
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
	/**
	 * Updates product information, ensuring new information doesn't match a 
	 * product already in the system under a different ID with the same store.
	 * @param product Product to try to add
	 * @throws DuplicateItemException If the product is a duplicate
	 */
	public void updateProduct(Product product) throws ProductDuplicateItemException{
		product.setName(convertToTitleCase(product.getName()));
//		if (doesProductAlreadyExist(product, true))
//			throw new ProductDuplicateItemException("Product already exists at this store.");
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
	/**
	 * Adds a product if it doesn't already exist.
	 * @param product Product to try to add
	 * @throws DuplicateItemException If the product is a duplicate
	 */
	public void addProduct(Product product) throws ProductDuplicateItemException {
		product.setName(convertToTitleCase(product.getName()));
		
		if (doesProductAlreadyExist(product, false))
			throw new ProductDuplicateItemException("Product already exists at this store.");
		
		storeInventoryRepository.insert(product);
	}
	
	/**
	 * Checks if the product already exists in the database as a different ID
	 * under the same store ID.
	 * @param product The product to add
	 * @param isUpdating If you are updating an existing product
	 * @return True if the product already exists; false otherwise.
	 */
	private boolean doesProductAlreadyExist(Product product, boolean isUpdating) {
		String storeID = product.getStore_id();
		String productName = product.getName();
		Collection<Product> matchedProducts = getProductByName(productName);
		
		for (Product match : matchedProducts) {
			if (match.getName().equals(productName) && match.getStore_id().equals(storeID))
			{
				if (isUpdating && match.get_id().equals(product.get_id()))
						return false;
				else
					return true;
			}
		}
		return false;
	}
	
	/**
	 * Converts a text to title case (ex: this method becomes This Method)
	 * @param text Text to convert
	 * @return Text in title case
	 */
	private String convertToTitleCase(String text) {
		StringBuilder converted = new StringBuilder();
		boolean nextIsCapital = true;
		for (char ch : text.toCharArray()) {
	        if (Character.isSpaceChar(ch))
	        	nextIsCapital = true;
	        else if (nextIsCapital) {
	            ch = Character.toTitleCase(ch);
	            nextIsCapital = false;
	        } 
	        else 
	            ch = Character.toLowerCase(ch);
	        converted.append(ch);
	    }
		return converted.toString();
	}
	
	@Override
	public Product findProductById(ObjectId id) {
		return storeInventoryRepository.findBy_id(id);
	}
}
