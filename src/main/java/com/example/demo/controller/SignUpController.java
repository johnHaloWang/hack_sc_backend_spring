package com.example.demo.controller;

import com.example.demo.converter.ConverterFacade;
import com.example.demo.model.Authority;
import com.example.demo.model.Geolocation;
import com.example.demo.model.Store;
import com.example.demo.model.User;
import com.example.demo.data.provider.StoreManager;
import com.example.demo.dto.RegisterDTO;
import com.example.demo.exceptions.StoreDuplicateItemException;
import com.example.demo.service.UserService;

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

@RestController
@RequestMapping("/api/register")
public class SignUpController {

	private final UserService service;
	private final ConverterFacade converterFacade;

	@Autowired
	private StoreManager storeManager;

	@Autowired
	public SignUpController(final UserService service, final ConverterFacade converterFacade) {
		this.service = service;
		this.converterFacade = converterFacade;
	}
	
	//need to clean this up
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> signUp(@RequestBody final RegisterDTO dto) throws StoreDuplicateItemException {
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
