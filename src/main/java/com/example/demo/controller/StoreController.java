/**
 * 
 */
package com.example.demo.controller;


import java.util.List;

import com.example.demo.DemoApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


import org.springframework.web.bind.annotation.RestController;
import com.example.demo.data.Geolocation;
import com.example.demo.data.Store;
import com.example.demo.data.provider.StoreManager;

import org.bson.types.ObjectId;


/**
 * @author johnhalowang
 *
 */

/**
 * This is the controller used by Spring framework.
 * <p>
 * The basic function of this controller is to map
 * each HTTP API Path to the correspondent method.
 *
 */
@RestController
@RequestMapping("/api/store")
public class StoreController {
	/**
    * When the class instance is annotated with
    * {@link Autowired}, it will be looking for the actual
    * instance from the defined beans.
    * <p>
    * In our project, all the beans are defined in
    * the {@link DemoApplication} class.
    */
   @Autowired
   private StoreManager storeManager;
   /**
    * This is a simple example of how to use a data manager
    * to retrieve the data and return it as an HTTP response.
    * <p>

    * <p>
    * Try it in your web browser:
    *     http://localhost:5000/john/store/store101
    */
   @RequestMapping(value = "/{storeId}", method = RequestMethod.GET)
   Store getStore(@PathVariable("storeId") ObjectId id) {

       Store store = storeManager.getStore(id);
       return store;
   }
   
   
   /**
    * This is an example of sending an HTTP POST request to
    * update a store's information 
    *
    * You can test this with a HTTP client by sending
    *  http://localhost:5000/john/store/user101
    *      name=store1 pictureFileName=file1 latitude=1.23 longitude=3.23
    *
    * Note, the URL will not work directly in browser, because
    * it is not a GET request. You need to use a tool such as
    * curl.
    *
    * @param id
    * @param name
    * @param pictureFileName
    * @param address
    * @param zipcode
    * @param city
    * @param state
    * @param latitude
    * @param longitude
    * @return store
    */
   @RequestMapping(value = "/{storeId}", method = RequestMethod.POST)
   Store updateStore(
           @PathVariable("storeId") ObjectId id,
           @RequestParam("name") String name,
           @RequestParam("pictureFileName") String pictureFileName,
           @RequestParam("address") String address,
           @RequestParam("zipcode") String zipcode,
           @RequestParam("city") String city,
           @RequestParam("state") String state,
           @RequestParam("latitude") float latitude,
           @RequestParam("longitude") float longitude){
	   
       Geolocation geolocation = new Geolocation();
       geolocation.setLatitude(latitude);
       geolocation.setLongitude(longitude);
       Store store = new Store(id, name, pictureFileName, geolocation, address,
				zipcode, city, state);
       storeManager.updateStore(store);
       return store;
   }
   
   
   /**
    * This API deletes the store. It uses HTTP DELETE method.
    *
    * @param storeId
    */
   @RequestMapping(value = "/{storeId}", method = RequestMethod.DELETE)
   void deleteStore(
           @PathVariable("storeId") ObjectId id) {
   	
	   storeManager.deleteStore(id);
   }
   
   
   /**
    * This API lists all the stores in the current database.
    *
    * @return
    */
   @RequestMapping(value = "/list", method = RequestMethod.GET)
   List<Store> listAllStores() {
   	
   		return storeManager.listAllStores();
   }
   

   /**
    * This API add a store in the current database.
    * @param name
    * @param pictureFileName
    * @param address
    * @param zipcode
    * @param city
    * @param state
    * @param latitude
    * @param longitude
    * @return store
    */
   @RequestMapping(value = "/add/{name}", method = RequestMethod.PUT)
   Store addStore(
           @RequestParam("name") String name,
           @RequestParam("pictureFileName") String pictureFileName,
           @RequestParam("address") String address,
           @RequestParam("zipcode") String zipcode,
           @RequestParam("city") String city,
           @RequestParam("state") String state,
           @RequestParam("latitude") float latitude,
           @RequestParam("longitude") float longitude){
	   
       Geolocation geolocation = new Geolocation();
       geolocation.setLatitude(latitude);
       geolocation.setLongitude(longitude);

       Store store = new Store(ObjectId.get(), name, pictureFileName, geolocation, address,
				zipcode, city, state);
       storeManager.addStore(store);
       return store;
   }
   
   /**
    * This API list a list of that closed to a store (store name)
    * based on the geolocation and radius in the current database.
    * @param name
    * @param latitude
    * @param longitude
    * @param radius
    * @return
    */
   
   @RequestMapping(value = "/listGeo/{name}", method = RequestMethod.GET)
	List<Store> listNameByRadius(
			@RequestParam("name") String name,
			@RequestParam("latitude") float latitude,
			@RequestParam("longitude") float longitude,
			@RequestParam("radius") float radius){
		
		Geolocation geo = new Geolocation();
		geo.setLatitude(latitude);
		geo.setLongitude(longitude);
		return storeManager.getStoreRadius(name, geo, radius);
	}
}
