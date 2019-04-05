package com.example.demo.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.data.Geolocation;
import com.example.demo.data.Store;
import com.example.demo.data.User;
import com.example.demo.data.provider.UserManager;

import com.example.demo.exceptions.UserDoesntExistedException;
import com.example.demo.exceptions.UserExistedException;
import com.example.demo.exceptions.UserPasswordMismatchedException;

import com.example.demo.data.provider.StoreManager;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@CrossOrigin(origins = "http://localhost/", maxAge = 3600)
@RestController
@RequestMapping("/api/user")
public class UserController {
	@Autowired
	private UserManager userManager;
	@Autowired
	private StoreManager storeManager;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	List<User> listAllUsers() {
		return userManager.listAllUsers();
	}

	@RequestMapping(value = "/{user_id}", method = RequestMethod.POST)
	User updateUser(@RequestParam("username") String username, @RequestParam("password") String password,
			@RequestParam("role") final String role, @RequestParam("contactNumber") String contactNumber,
			@RequestParam("store_id") String store_id, @RequestParam("email") String email)
			throws UserExistedException {

		User user = new User(ObjectId.get(), username, password, store_id, contactNumber, role, email);
		userManager.updateUser(user);
		return user;
	}

	@RequestMapping(value = "/{user_id}", method = RequestMethod.DELETE)
	void deleteUser(@PathVariable("user_id") ObjectId id) throws UserDoesntExistedException {

		userManager.deleteUser(id);
	}

	@RequestMapping(value = "/{user_id}", method = RequestMethod.GET)
	User getUser(@PathVariable("{user_id}") ObjectId id) throws UserDoesntExistedException {

		User user = userManager.findUserById(id);
		return user;
	}

	@RequestMapping(value = "/add/{username}", method = RequestMethod.PUT)
	User addUser(@PathVariable("username") String username, @RequestParam("password") String password,
			@RequestParam("role") final String role, @RequestParam("contactNumber") String contactNumber,
			@RequestParam("store_id") String store_id, @RequestParam("name") String name,
			@RequestParam("email") String email)
			throws ServletException, UserExistedException, UserDoesntExistedException, UserPasswordMismatchedException {

		User user = new User(ObjectId.get(), username, password, store_id, contactNumber, role, email);
		userManager.addUser(user);
		return user;
	}

	@RequestMapping(value = "/register/", method = RequestMethod.PUT)
	String register(@RequestParam("username") String username, @RequestParam("password") String password,
			@RequestParam("role") final String role, @RequestParam("contactNumber") String contactNumber,
			@RequestParam("name") String name, @RequestParam("pictureFileName") String pictureFileName,
			@RequestParam("address") String address, @RequestParam("zipcode") String zipcode,
			@RequestParam("city") String city, @RequestParam("state") String state, @RequestParam("email") String email,
			@RequestParam("latitude") float latitude, @RequestParam("longitude") float longitude)
			throws ServletException, UserExistedException, UserDoesntExistedException, UserPasswordMismatchedException {

		Geolocation geolocation = new Geolocation();
		geolocation.setLatitude(latitude);
		geolocation.setLongitude(longitude);
		ObjectId store_id = ObjectId.get();
		Store store = new Store(store_id, name, pictureFileName, geolocation, address, zipcode, city, state);
		storeManager.addStore(store);
		User user = new User(ObjectId.get(), username, password, store.get_id(), contactNumber, role, email);

		userManager.addUser(user);
		return login(user.getUsername(), user.getPassword());
	}

	@RequestMapping(value = "/add/", method = RequestMethod.PUT)
	void addUser(@RequestParam("username") String username, @RequestParam("password") String password,
			@RequestParam("role") final String role, @RequestParam("contactNumber") String contactNumber,
			@RequestParam("name") String name, @RequestParam("pictureFileName") String pictureFileName,
			@RequestParam("address") String address, @RequestParam("zipcode") String zipcode,
			@RequestParam("city") String city, @RequestParam("state") String state, @RequestParam("email") String email,
			@RequestParam("latitude") float latitude, @RequestParam("longitude") float longitude)
			throws UserExistedException {

		Geolocation geolocation = new Geolocation();
		geolocation.setLatitude(latitude);
		geolocation.setLongitude(longitude);
		ObjectId store_id = ObjectId.get();
		Store store = new Store(store_id, name, pictureFileName, geolocation, address, zipcode, city, state);
		storeManager.addStore(store);
		User user = new User(ObjectId.get(), username, password, store.get_id(), contactNumber, role, email);
		userManager.addUser(user);
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(@RequestParam("username") final String username,
			@RequestParam("password") final String password)
			throws ServletException, UserDoesntExistedException, UserPasswordMismatchedException {
		String jwtToken = "";

		if (username == null || password == null) {
			throw new ServletException("Please fill in username and password");
		}

		User user = userManager.findUserByUsername(username);

		String pwd = user.getPassword();

		if (!password.equals(pwd))
			throw new UserPasswordMismatchedException("Invalid login. Wrong password.");

		jwtToken = Jwts.builder().setSubject(username).claim("roles", "user").setIssuedAt(new Date())
				.signWith(SignatureAlgorithm.HS256, "secretkey").compact();

		return jwtToken;
	}

}
