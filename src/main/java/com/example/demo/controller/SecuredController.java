package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.converter.ConverterFacade;
import com.example.demo.model.Authority;
import com.example.demo.model.Geolocation;
import com.example.demo.model.Store;
import com.example.demo.model.User;
import com.example.demo.data.provider.StoreManager;
import com.example.demo.dto.RegisterDTO;
import com.example.demo.exceptions.StoreDuplicateItemException;
import com.example.demo.exceptions.UserDoesntExistException;
import com.example.demo.exceptions.UserExistedException;
import com.example.demo.exceptions.UserPasswordMismatchedException;
import com.example.demo.service.UserService;

import com.example.demo.dto.IndexDTO;

@RestController
@RequestMapping("/api/user")
public class SecuredController {

	
	private final UserService service;
	private final ConverterFacade converterFacade;
	@Autowired
	private StoreManager storeManager;
	
	@Autowired
	public SecuredController (final UserService service, final ConverterFacade converterFacade) {
		this.service = service;
		this.converterFacade = converterFacade;
	}
	
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> sayHello() {
        return new ResponseEntity<>("Secured hello!", HttpStatus.OK);
    }
    
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	List<User> listAllUsers() {
		return service.findAll();
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public ResponseEntity<?> updateUser(@RequestBody final RegisterDTO dto){
		
		User user = new User();
		ObjectId key = new ObjectId(dto.get_id());
		user.set_id(key);
		user.setUsername(dto.getUsername());
		user.setPassword(dto.getPassword());
		user.setStore_id(dto.getStore_id());
		user.setContactNumber(dto.getContactNumber());
		user.setAccountNonExpired(false);
		user.setCredentialsNonExpired(false);
		user.setEnabled(true);
		user.setRole(dto.getRole());

		//this is just stupid....
		List<Authority> authorities = new ArrayList<>();
			if (user.getRole().equals("ROLE_USER"))
				authorities.add(Authority.ROLE_USER);
			else if (user.getRole().equals("ROLE_ADMIN"))
				authorities.add(Authority.ROLE_ADMIN);
			else if (user.getRole().equals("ANONYMOUS"))
				authorities.add(Authority.ANONYMOUS);

		user.setAuthorities(authorities);
		user.setEmail(dto.getEmail());
		User find = service.update(key, user);

		return new ResponseEntity<>(find, HttpStatus.OK);
		
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public ResponseEntity<?> deleteUser(@RequestBody final IndexDTO dto){
		String index = service.delete(dto.get_id());
		return new ResponseEntity<>(index, HttpStatus.OK);
		
	}
	
	@RequestMapping(value = "/get", method = RequestMethod.POST)
	public ResponseEntity<?> getUser(@RequestBody final IndexDTO dto){
		User find = service.find(dto.get_id());
		return new ResponseEntity<>(find, HttpStatus.OK);	
	}

	@RequestMapping(value = "/addUserToStore", method = RequestMethod.POST)
	public ResponseEntity<?> addUserToAStore(@RequestBody final RegisterDTO dto){
		User user = new User();
		ObjectId key = new ObjectId(dto.get_id());
		user.set_id(key);
		user.setUsername(dto.getUsername());
		user.setPassword(dto.getPassword());
		user.setStore_id(dto.getStore_id());
		user.setContactNumber(dto.getContactNumber());
		user.setAccountNonExpired(false);
		user.setCredentialsNonExpired(false);
		user.setEnabled(true);
		user.setRole(dto.getRole());

		//this is just stupid....
		List<Authority> authorities = new ArrayList<>();
			if (user.getRole().equals("ROLE_USER"))
				authorities.add(Authority.ROLE_USER);
			else if (user.getRole().equals("ROLE_ADMIN"))
				authorities.add(Authority.ROLE_ADMIN);
			else if (user.getRole().equals("ANONYMOUS"))
				authorities.add(Authority.ANONYMOUS);

		user.setAuthorities(authorities);
		user.setEmail(dto.getEmail());
		User find = service.create(user);

		return new ResponseEntity<>(find, HttpStatus.OK);	
	}

	@RequestMapping(value = "/addNewStore", method = RequestMethod.POST)
	public ResponseEntity<?> addNewStore(@RequestBody final RegisterDTO dto) throws StoreDuplicateItemException {
		Geolocation geolocation = new Geolocation();
		geolocation.setLatitude(dto.getLatitude());
		geolocation.setLongitude(dto.getLongitude());
		ObjectId store_id = ObjectId.get();
		Store store = new Store(store_id, dto.getName(), dto.getPictureFileName(), geolocation, dto.getAddress(),
				dto.getZipcode(), dto.getCity(), dto.getState());

		storeManager.addStore(store);

		User user = new User();
		user.set_id(ObjectId.get());
		user.setUsername(dto.getUsername());
		user.setPassword(dto.getPassword());
		user.setStore_id(store.get_id());
		user.setContactNumber(dto.getContactNumber());
		user.setAccountNonExpired(false);
		user.setCredentialsNonExpired(false);
		user.setEnabled(true);
		user.setRole(dto.getRole());

		//this is just stupid....
		List<Authority> authorities = new ArrayList<>();
			if (user.getRole().equals("ROLE_USER"))
				authorities.add(Authority.ROLE_USER);
			else if (user.getRole().equals("ROLE_ADMIN"))
				authorities.add(Authority.ROLE_ADMIN);
			else if (user.getRole().equals("ANONYMOUS"))
				authorities.add(Authority.ANONYMOUS);

		user.setAuthorities(authorities);
		user.setEmail(dto.getEmail());
		User find = service.create(user);

		return new ResponseEntity<>(find, HttpStatus.OK);
	
	}
}

