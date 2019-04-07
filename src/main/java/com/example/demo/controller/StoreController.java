/**
 * 
 */
package com.example.demo.controller;


import java.io.IOException;
import java.util.List;

import com.example.demo.DemoApplication;
import com.example.demo.converter.ConverterFacade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.bind.annotation.RestController;

import com.example.demo.data.provider.StoreManager;
import com.example.demo.dto.GeoRequestDTO;
import com.example.demo.dto.IndexDTO;
import com.example.demo.dto.StoreDTO;

import com.example.demo.exceptions.StoreDuplicateItemException;
import com.example.demo.model.Store;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
	Logger logger = LogManager.getLogger(AuthenticationController.class);
	private final ConverterFacade converterFacade;
	
	@Autowired
	public StoreController(final ConverterFacade converterFacade) {

		this.converterFacade = converterFacade;
	}
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
   
   @RequestMapping(value = "/get", method = RequestMethod.GET)
	public ResponseEntity<?> getStore(@RequestBody final IndexDTO dto) {

		return new ResponseEntity<>(storeManager.getStore(dto.get_id()), HttpStatus.OK);
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
 * @throws StoreDuplicateItemException 
    */
   
   
   @RequestMapping(value = "/update", method = RequestMethod.POST)
	public ResponseEntity<?> updateStore(@RequestBody final StoreDTO dto) throws StoreDuplicateItemException {

		Store store = converterFacade.convertStoreDTO(dto);
		storeManager.updateStore(store);
		return new ResponseEntity<>(store, HttpStatus.OK);
	}
   
   
   
   /**
    * This API deletes the store. It uses HTTP DELETE method.
    *
    * @param storeId
    */
   
   @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteProduct(@RequestBody final IndexDTO dto) {

		storeManager.deleteStore(dto.get_id());
		return new ResponseEntity<>(dto.get_id().toHexString(), HttpStatus.OK);
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
 * @throws StoreDuplicateItemException 
    */
   
   @RequestMapping(value = "/add", method = RequestMethod.PUT)
	public ResponseEntity<?> addProduct(@RequestBody final StoreDTO dto) throws StoreDuplicateItemException {

		Store store = converterFacade.convertStoreDTO(dto);
		store.set_id(ObjectId.get());
		storeManager.addStore(store);
		return new ResponseEntity<>(store, HttpStatus.OK);
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
   @RequestMapping(value = "/listGeo", method = RequestMethod.GET)
	public ResponseEntity<?> listGeo(@RequestBody final GeoRequestDTO dto) throws IOException {

		return new ResponseEntity<>(
				storeManager.getStoreRadius(dto.getName(), dto.getGeolocation(), dto.getRadius()),
				HttpStatus.OK);
	}
   
}
