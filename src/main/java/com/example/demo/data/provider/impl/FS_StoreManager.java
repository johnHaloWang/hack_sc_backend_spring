
/**
 * 
 */
package com.example.demo.data.provider.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.bson.types.ObjectId;

import com.example.demo.exceptions.StoreDuplicateItemException;
import com.example.demo.data.Geolocation;
import com.example.demo.data.Store;
import com.example.demo.data.provider.StoreManager;
import com.example.demo.data.repository.StoreRepository;


/**
 * The implementation of {@link StoreManager} interface
 * using file system.
 * <p>
 * This class demonstrates how you can use the file system
 * as a database to store your data.
 * 
 * @author johnhalowang, Lisa Chen
 */


public class FS_StoreManager implements StoreManager {

		
	@Autowired
	private StoreRepository storeRepository;
	
	@Override
	public Store getStore(ObjectId storeId) {
		return this.storeRepository.findBy_id(storeId);
	}

	@Override
	/**
	 * Updates store information, ensuring new information doesn't match a 
	 * store already in the system under a different ID.
	 * @param store Store to add
	 * @throws DuplicateItemException If the product is a duplicate
	 */
	public void updateStore(Store store) throws StoreDuplicateItemException{	
		store.setName(convertToTitleCase(store.getName()));

		if (doesStoreAlreadyExist(store))
			throw new StoreDuplicateItemException("This store address already exisit, please enter another one");
		
		storeRepository.save(store);
	}

	@Override
	public void deleteStore(ObjectId storeId) {
		storeRepository.delete(storeRepository.findBy_id(storeId));
	}

	@Override
	public List<Store> listAllStores() {
		return storeRepository.findAll();
	}
	
	@Override
	/**
	 * Adds a new store, if it doesn't already exist.
	 * @param store Store to add
	 * @throws DuplicateItemException If the product is a duplicate
	 */
	public void addStore(Store store) throws StoreDuplicateItemException {
		/*
		 * TODO need to add verification for address input (probably through 
		 * the geolocation check). Also ensure formatting is standardized for 
		 * the match.
		 */
		store.setName(convertToTitleCase(store.getName()));
		
		if (doesStoreAlreadyExist(store))
			throw new StoreDuplicateItemException("This store address already exisit, please enter another one");
		
		storeRepository.insert(store);
	}
	
	/**
	 * Checks if the store already exists in the database as a different ID.
	 * @param store The store to add
	 * @return True if the store already exists; false otherwise.
	 */
	private boolean doesStoreAlreadyExist(Store store) {
		String storeAddress = store.getStoreAddress();
		String storeZipCode = store.getZipcode();
		String storeID = store.get_id();
		Collection<Store> matchedStores = getStoreByName(store.getName());
		
		for (Store match : matchedStores) {
			if (match.getStoreAddress().equals(storeAddress) && 
					match.getZipcode().equals(storeZipCode)) {
				if (match.get_id() == null)
					return true;
				else if (match.get_id() != null && !match.get_id().equals(storeID))
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
	public Collection<Store> getStoreByName(String name) {
		return storeRepository.findByNameLike(name);
	}
	
	@Override
	public List<Store>getStoreRadius(String storeName, Geolocation geo, double miles){
		List<Store> list = new ArrayList<Store>();
		Collection<Store> collection = getStoreByName(storeName);
		for (Store store: collection) {
			if(miles>calcGPSDistance(store.getGeolocation(), geo))
				list.add(store);
		}
		return list;
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

}
