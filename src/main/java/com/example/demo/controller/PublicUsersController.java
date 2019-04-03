package com.example.demo.controller;

import com.example.demo.auth.api.UserAuthenticationService;
import com.example.demo.user.crud.api.UserCrudService;
import com.example.demo.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.data.provider.StoreManager;
import com.example.demo.data.Geolocation;
import com.example.demo.data.Store;

import java.util.UUID;

import static lombok.AccessLevel.PACKAGE;
import static lombok.AccessLevel.PRIVATE;

@RestController
@RequestMapping("/public/users")
@FieldDefaults(level = PRIVATE, makeFinal = true)
@AllArgsConstructor(access = PACKAGE)
final class PublicUsersController {
	@NonNull
	UserAuthenticationService authentication;
	@NonNull
	UserCrudService users;
	@Autowired
	private StoreManager storeManager;

	@PostMapping("/register")
	String register(@RequestParam("username") final String username, @RequestParam("password") final String password,
			@RequestParam("role") final String role, @RequestParam("contactNumber") final String contactNumber,
			@RequestParam("name") String name, @RequestParam("pictureFileName") String pictureFileName,
			@RequestParam("address") String address, @RequestParam("zipcode") String zipcode,
			@RequestParam("city") String city, @RequestParam("state") String state,
			@RequestParam("latitude") float latitude, @RequestParam("longitude") float longitude) {

		Geolocation geolocation = new Geolocation();
		geolocation.setLatitude(latitude);
		geolocation.setLongitude(longitude);
		ObjectId store_id = ObjectId.get();
		Store store = new Store(store_id, name, pictureFileName, geolocation, address, zipcode, city, state);
		storeManager.addStore(store);

		users.save(User.Builder.builder().setId(ObjectId.get()).setUsername(username).setPassword(password)
				.setRole(role).setStore_id(store_id.toHexString()).build());

		return login(username, password);
	}

	@PostMapping("/login")
	String login(@RequestParam("username") final String username, @RequestParam("password") final String password) {
		return authentication.login(username, password)
				.orElseThrow(() -> new RuntimeException("invalid login and/or password"));
	}
}