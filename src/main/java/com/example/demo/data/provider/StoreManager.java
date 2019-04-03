/**
 * 
 */
package com.example.demo.data.provider;

import org.bson.types.ObjectId;

import java.util.Collection;
import java.util.List;

import com.example.demo.data.Geolocation;
import com.example.demo.data.Store;

/**
 * @author johnhalowang
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
	 * Update the given user object and persist it.
	 * <p>
	 * If the user does not exist before, this
	 * method will create a new record; otherwise,
	 * it will overwrite whatever is currently
	 * being stored.
	 *
	 * @param store object
	 */
	public void updateStore(Store store);

	/**
	 * Delete the given store from the storage.
	 *
	 * @param storeId
	 */
	public boolean deleteStore(ObjectId storeId);

	/**
	 * List all the current stores in the storage.
	 *
	 * @return
	 */
	public List<Store> listAllStores();
	public void addStore(Store store);
	public Collection<Store>getStoreByName(String name);
	public List<Store>getStoreRadius(String storeName, Geolocation geo, double miles);
	
}
