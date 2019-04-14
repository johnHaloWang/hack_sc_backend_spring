package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.converter.ConverterFacade;
import com.example.demo.model.Authority;
import com.example.demo.model.Store;
import com.example.demo.model.User;
import com.example.demo.data.provider.StoreManager;
import com.example.demo.dto.LoginDTO;
import com.example.demo.dto.RegisterDTO;
import com.example.demo.exceptions.StoreDuplicateItemException;
import com.example.demo.service.UserService;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import com.example.demo.dto.IndexDTO;

@RestController
@RequestMapping("/api/user")
public class SecuredController {

	Logger logger = LogManager.getLogger(AuthenticationController.class);
	private final UserService service;
	private final ConverterFacade converterFacade;
	@Autowired
	private StoreManager storeManager;

	@Autowired
	public SecuredController(final UserService service, final ConverterFacade converterFacade) {
		this.service = service;
		this.converterFacade = converterFacade;
	}
	
	
	
	@RequestMapping(value = "/getUsername", method = RequestMethod.POST)
	public ResponseEntity<?> getUsername(@RequestBody final LoginDTO dto) {

		User user = service.findByUsername(dto.getUsername());
		return new ResponseEntity<>(user, HttpStatus.OK);
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	List<User> listAllUsers() {
		return service.findAll();
	}

	/**
	 * 
	 * @param dto
	 * @return
	 * 
          { 
	          "_id": "5ca97198bee20412a4f0ffe4", "username": "halo23",
	          "password": "halo23", "store_id": "5ca97198bee20412a4f0ffe2",
	          "contactNumber": "6262678982", "role": "ROLE_ADMIN", "email":
	          "hello.wang@cpp.edu", "enabled": true  
          }
	 * 
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public ResponseEntity<?> updateUser(@RequestBody final RegisterDTO dto) {

		final User user = new User();

		user.setUsername(dto.getUsername());
		user.setPassword(dto.getPassword());
		user.setAccountNonExpired(false);
		user.setCredentialsNonExpired(false);
		user.setEnabled(true);

		List<Authority> authorities = new ArrayList<>();
		if (user.getRole().equals("ROLE_USER"))
			authorities.add(Authority.ROLE_USER);
		else if (user.getRole().equals("ROLE_ADMIN"))
			authorities.add(Authority.ROLE_ADMIN);
		else if (user.getRole().equals("ANONYMOUS"))
			authorities.add(Authority.ANONYMOUS);

		user.setAuthorities(authorities);
		// User user = converterFacade.convertRegisterDTO(dto);
		ObjectId key = new ObjectId(user.get_id());
		return new ResponseEntity<>(service.update(key, user), HttpStatus.OK);

	}

	/**
	 * 
	 * @param dto
	 * @return { "_id": "5ca97198bee20412a4f0ffe4"
	 * 
	 *         }
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public ResponseEntity<?> deleteUser(@RequestBody final IndexDTO dto) {
		String index = service.delete(dto.get_id());
		return new ResponseEntity<>(index, HttpStatus.OK);

	}

	/**
	 * 
	 * @param dto
	 * @return
	 * 
	 *         { "_id": "5ca6a49e920ede02679e43a7" }
	 * 
	 */
	@RequestMapping(value = "/get", method = RequestMethod.GET)
	public ResponseEntity<?> getUser(@RequestBody final IndexDTO dto) {
		User find = service.find(dto.get_id());
		return new ResponseEntity<>(find, HttpStatus.OK);
	}

	/**
	 * 
	 * @param dto
	 * @return
	 * 
	 *         it doesn't update the store information it requires to have store_id
	 * 
	 * 
          jason input pattern 1
  
          { 
	          "username": "halo9", 
	          "password": "halo9",
	          "contactNumber":"6262678982", 
	          "role": "ROLE_ADMIN", 
	          "email":"halo@gamil.com", 
	          "store_id": "5ca96bdfbee204128f3762da", 
	          "store":{ }
          }
	  
          jason input pattern 2 
          
          { 
          		"username": "halo5", 
          		"password": "halo5",
          		"contactNumber":"6262678982", 
          		"role": "ROLE_ADMIN", 
          		"email":"halo@gamil.com", 
          		"store":{ 
          			"name": "new Xxxx-v5", 
          			"pictureFileName":"sdfa", 
          			"address": "asdfa", 
          			"zipcode": "afdsa", 
          			"city": "adfadfs",
          			"state": "afdsasf", 
          			"geolocation":{ 
          				"latitude": 23.229999542236328,
          				"longitude": 32.22999954223633 
          				}, 
          			"storeAddress": "asdfa adfadfsafdsa, afdsasf" 
          	}
  
          }
	 */

	@RequestMapping(value = "/addUserToStore", method = RequestMethod.POST)
	public ResponseEntity<?> addUserToAStore(@RequestBody final RegisterDTO dto) {

		User user = converterFacade.convertRegisterDTO(dto);
		return new ResponseEntity<>(service.create(user), HttpStatus.OK);
	}

	/**
	 * 
	 * @param dto
	 * @return
	 * @throws StoreDuplicateItemException
	 * 
	 * 
	 * 
             { 
	             "username": "halo10", "password":
	              "halo10", "contactNumber":"6262678982",
	              "role": "ROLE_ADMIN", "email":
	              "halo@gamil.com", "store":{ "name": "new
	              Xxxx-v10", "pictureFileName": "sdfa",
	              "address": "asdfa", "zipcode": "afdsa",
	              "city": "adfadfs", "state": "afdsasf",
	              "geolocation":{ 
		              "latitude":23.229999542236328, 
		              "longitude":32.22999954223633 
	              }, 
	              "storeAddress":"asdfa adfadfs afdsa, afdsasf" 
	              }
             }
	 * 
	 * 
	 * 
	 */
	@RequestMapping(value = "/addNewStoreAndNewUser", method = RequestMethod.POST)
	public ResponseEntity<?> addNewStore(@RequestBody final RegisterDTO dto) throws StoreDuplicateItemException {

		ObjectId store_id = ObjectId.get();
		Store store = converterFacade.convertStoreDTO(dto.getStore());
		store.set_id(store_id);
		storeManager.addStore(store);
		User user = converterFacade.convertRegisterDTO(dto);
		user.set_id(ObjectId.get());
		user.setStore_id(store_id.toHexString());

		return new ResponseEntity<>(service.create(user), HttpStatus.OK);

	}
}