/**
 * 
 */
package com.example.demo.data.provider;

import org.bson.types.ObjectId;

import java.util.Collection;
import java.util.List;

import com.example.demo.exceptions.StoreDuplicateItemException;
import com.example.demo.model.Geolocation;
import com.example.demo.model.Product;
import com.example.demo.model.Store;

/**
 * @author johnhalowang, Lisa Chen
 *
 */
public interface StoreManager {

	/**
	 * Get the store information object based on
	 * the given storeId.
	 * <p>
	 * If the store does not exist, simply create
	 * one.
	 *
	 * @param storeId
	 * @return the Store object
	 */
	public Store getStore(ObjectId StoreId);
	
	/**
	 * Gets all the products associated with the store.
	 * @return The list of products
	 */
	public List<Product> getAllProducts(ObjectId StoreId);

	/**
	 * Update the given user object and persist it.
	 * <p>
	 * If the user does not exist before, this
	 * method will create a new record; otherwise,
	 * it will overwrite whatever is currently
	 * being stored.
	 *
	 * @param store object
	 */
	public void updateStore(Store store) throws StoreDuplicateItemException ;

	/**
	 * Delete the given store from the storage.
	 *
	 * @param storeId
	 */
	public void deleteStore(ObjectId storeId);

	/**
	 * List all the current stores in the storage.
	 *
	 * @return
	 */
	public List<Store> listAllStores();
	public void addStore(Store store) throws StoreDuplicateItemException;
	public Collection<Store>getStoreByName(String name);
	public List<Store>getStoreRadius(String storeName, Geolocation geo, double miles);
	
}
