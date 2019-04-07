package com.example.demo.controller;

import com.example.demo.converter.ConverterFacade;
import com.example.demo.model.Authority;
import com.example.demo.model.Store;
import com.example.demo.model.User;
import com.example.demo.data.provider.StoreManager;
import com.example.demo.dto.RegisterDTO;
import com.example.demo.exceptions.StoreDuplicateItemException;
import com.example.demo.service.UserService;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;

@RestController
@RequestMapping("/api/register")
public class SignUpController {

	Logger logger = LogManager.getLogger(AuthenticationController.class);
	private final UserService service;
	private final ConverterFacade converterFacade;

	@Autowired
	private StoreManager storeManager;

	@Autowired
	public SignUpController(final UserService service, final ConverterFacade converterFacade) {
		this.service = service;
		this.converterFacade = converterFacade;
	}
	
	/**
	 * 
	 * @param dto
	 * @return
	 * @throws StoreDuplicateItemException
	 * json input example 
{
    "username": "halo4",
    "password": "halo4",
	"contactNumber":"6262678982",
	"role": "ROLE_ADMIN",
	"email": "halo@gamil.com",
	"store":{
    	"name": "new Xxxx-v4",
	    "pictureFileName": "sdfa",
	    "address": "asdfa",
	    "zipcode": "afdsa",
	    "city": "adfadfs",
	    "state": "afdsasf",
	    "geolocation":{
		    "latitude": 23.229999542236328,
		    "longitude": 32.22999954223633
	    },
	    "storeAddress": "asdfa adfadfs afdsa, afdsasf"
	}
    
}
	 */
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> signUp(@RequestBody final RegisterDTO dto) throws StoreDuplicateItemException {

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
