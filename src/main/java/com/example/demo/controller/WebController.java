package com.example.demo.controller;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.net.InetAddress;


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


import com.example.demo.data.Geolocation;
import com.example.demo.data.Store;
import com.example.demo.data.provider.StoreManager;

import org.bson.types.ObjectId;





/**
 * This is the controller used by Spring framework.
 * <p>
 * The basic function of this controller is to map
 * each HTTP API Path to the correspondent method.
 *
 */

@RestController
public class WebController {
   
	/**
     * When the class instance is annotated with
     * {@link Autowired}, it will be looking for the actual
     * instance from the defined beans.
     * <p>
     * In our project, all the beans are defined in
     * the {@link DemoApplication} class.
     */
//    @Autowired
////    private StoreRepository storeRepository;
//    @Autowired
//    private StoreManager storeManager;

    
    
	@RequestMapping(value = "/server/ping", method = RequestMethod.GET)
    String healthCheck() {
        // You can replace this with other string,
        // and run the application locally to check your changes
        // with the URL: http://localhost:5000/  if run local server
		// with the URL: http://[aws url]:5000/  if run on aws elastic beanstalk
        return "OK";
    }
	@RequestMapping(value = "/server/hello", method = RequestMethod.GET)
    String getGreeting()
    {
       return "Hello, world!";
    }
	
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
//    @RequestMapping(value = "/json/store/{storeId}", method = RequestMethod.GET)
//    Store getStore(@PathVariable("storeId") String id) {
//    	//json file database method
//        Store store = storeManager.getStore(id);
//
//        return store;
//    }
    
    
    /**
     * This is an example of sending an HTTP POST request to
     * update a store's information (or create the user if not
     * exists before).
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
//    @RequestMapping(value = "/json/store/{storeId}", method = RequestMethod.POST)
//    Store updateStore(
//            @PathVariable("storeId") String id,
//            @RequestParam("name") String name,
//            @RequestParam("pictureFileName") String pictureFileName,
//            @RequestParam("latitude") float latitude,
//            @RequestParam("longitude") float longitude){
//        Store store = new Store();
//        //ObjectId = require('mongodb').ObjectID;
//        
//        store.setString_id(id);;
//        //store.set_id(ObjectId.get());
//        store.setName(name);
//        store.setPictureFileName(pictureFileName);
//        Geolocation loc = new Geolocation();
//        loc.setLatitude(latitude);
//        loc.setLongitude(longitude);
//        store.setLocation(loc);
//        
//        //json file database method
//        storeManager.updateStore(store);
//
//        return store;
//    }
    
    
    /**
     * This API deletes the store. It uses HTTP DELETE method.
     *
     * @param storeId
     */
//    @RequestMapping(value = "/json/store/{storeId}", method = RequestMethod.DELETE)
//    void deleteStore(
//            @PathVariable("storeId") String id) {
//    	
//    	//json file database method
//        storeManager.deleteStore(id);
//    }
    
    /**
     * This API lists all the stores in the current database.
     *
     * @return
     */
//    @RequestMapping(value = "/json/store/list", method = RequestMethod.GET)
//    List<Store> listAllStores() {
//    	//json file database method
//        return storeManager.listAllStores();
//    }
    
    
    
    
    
    
    
    
    
    
    
}
