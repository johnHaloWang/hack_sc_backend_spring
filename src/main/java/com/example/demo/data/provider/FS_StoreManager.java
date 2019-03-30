
/**
 * 
 */
package com.example.demo.data.provider;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//import org.bson.types.ObjectId;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.example.demo.data.Store;
import com.example.demo.data.StoreMap;
import com.example.demo.util.ResourceResolver;


/**
 * The implementation of {@link StoreManager} interface
 * using file system.
 * <p>
 * This class demonstrates how you can use the file system
 * as a database to store your data.
 * 
 * @author johnhalowang
 */


public class FS_StoreManager implements StoreManager {

	/* (non-Javadoc)
	 * @see com.example.demo.data.provider.StoreManager#getStore(java.lang.String)
	 */
	
	/**
	 * We persist all the stores related objects as JSON.
	 * <p>
	 * For more information about JSON and ObjectMapper, please see:
	 * http://www.journaldev.com/2324/jackson-json-processing-api-in-java-example-tutorial
	 *
	 * or Google tons of tutorials
	 *
	 */
	private static final ObjectMapper JSON = new ObjectMapper();
	
	/**
	 * Load the store map from the local file.
	 *
	 * @return
	 */
	private StoreMap getStoreMap() {
		StoreMap storeMap = null;
		File storeFile = ResourceResolver.getStoreFile();
		if (storeFile.exists()) {
			// read the file and convert the JSON content
			// to the UserMap object
			try {
				storeMap = JSON.readValue(storeFile, StoreMap.class);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			storeMap = new StoreMap();
		}
		return storeMap;
	}
	
	/**
	 * Save and persist the user map in the local file.
	 *
	 * @param userMap
	 */
	private void persistStoreMap(StoreMap storeMap) {
		try {
			// convert the Store object to JSON format
			JSON.writeValue(ResourceResolver.getStoreFile(), storeMap);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public Store getStore(String storeId) {
		// TODO Auto-generated method stub
		StoreMap storeMap = getStoreMap();
		return storeMap.get(storeId);
	}

	/* (non-Javadoc)
	 * @see com.example.demo.data.provider.StoreManager#updateStore(com.example.demo.data.Store)
	 */
	@Override
	public void updateStore(Store store) {
		// TODO Auto-generated method stub
		StoreMap storeMap = getStoreMap();
		storeMap.put(store.getString_id(), store);
		persistStoreMap(storeMap);

	}

	/* (non-Javadoc)
	 * @see com.example.demo.data.provider.StoreManager#deleteStore(java.lang.String)
	 */
	@Override
	public void deleteStore(String storeId) {
		// TODO Auto-generated method stub
		StoreMap storeMap = getStoreMap();
		storeMap.remove(storeId);
		persistStoreMap(storeMap);

	}

	/* (non-Javadoc)
	 * @see com.example.demo.data.provider.StoreManager#listAllStores()
	 */
	@Override
	public List<Store> listAllStores() {
		// TODO Auto-generated method stub
		StoreMap storeMap = getStoreMap();
		return new ArrayList<Store>(storeMap.values());
	}

}
