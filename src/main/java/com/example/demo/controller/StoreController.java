/**
 * 
 */
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
import com.example.demo.data.Store;
//import com.example.demo.data.provider.StoreManager;

import com.example.demo.data.repository.StoreRepository;
import org.bson.types.ObjectId;


/**
 * @author johnwang
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
@RequestMapping("/store")
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
   private StoreRepository storeRepository;
   /**
    * This is a simple example of how to use a data manager
    * to retrieve the data and return it as an HTTP response.
    * <p>
    * Note, when it returns from the Spring, it will be
    * automatically converted to JSON format.
    * <p>
    * Try it in your web browser:
    *     http://localhost:5000/john/store/store101
    */
   @RequestMapping(value = "/{storeId}", method = RequestMethod.GET)
   Store getStore(@PathVariable("storeId") ObjectId id) {
   	//json file database method
       //Store store = storeManager.getStore(storeId);
   	
   	//using MongoRepository to handle it
       Store store = storeRepository.findBy_id(id);
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
    * @param latitude
    * @param longitude
    * @return
    */
   @RequestMapping(value = "/{storeId}", method = RequestMethod.POST)
   Store updateStore(
           @PathVariable("storeId") ObjectId id,
           @RequestParam("name") String name,
           @RequestParam("pictureFileName") String pictureFileName,
           @RequestParam("latitude") float latitude,
           @RequestParam("longitude") float longitude){
       Store store = new Store();
       store.set_id(id);
       store.setName(name);
       store.setPictureFileName(pictureFileName);
       Geolocation loc = new Geolocation();
       loc.setLatitude(latitude);
       loc.setLongitude(longitude);
       store.setLocation(loc);
       
       //using MongoRepository to handle it
       this.storeRepository.save(store);
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
   	
   	//json file database method
       //storeManager.deleteStore(storeId);
   	
   	//using MongoRepository to handle it
   	storeRepository.delete(storeRepository.findBy_id(id));
   }
   
   
   /**
    * This API lists all the stores in the current database.
    *
    * @return
    */
   @RequestMapping(value = "/list", method = RequestMethod.GET)
   List<Store> listAllStores() {
   	
   	
   	//using MongoRepository to handle it
   	return storeRepository.findAll();

   }
   /**
    * This API add a store in the current database.
    * @param name
    * @param pictureFileName
    * @param latitude
    * @param longitude
    * @return store
    */
   @RequestMapping(value = "/add/{name}", method = RequestMethod.PUT)
   Store addStore(
           @RequestParam("name") String name,
           @RequestParam("pictureFileName") String pictureFileName,
           @RequestParam("latitude") float latitude,
           @RequestParam("longitude") float longitude){
       Store store = new Store();
       store.set_id(ObjectId.get());
       store.setName(name);
       store.setPictureFileName(pictureFileName);
       Geolocation loc = new Geolocation();
       loc.setLatitude(latitude);
       loc.setLongitude(longitude);
       store.setLocation(loc);
       
       //using MongoRepository to handle it
       this.storeRepository.insert(store);

       return store;
   }
}
