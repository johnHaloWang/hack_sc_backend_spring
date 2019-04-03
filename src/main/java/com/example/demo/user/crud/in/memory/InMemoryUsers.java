package com.example.demo.user.crud.in.memory;

import com.example.demo.user.crud.api.UserCrudService;
import com.example.demo.user.entity.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import org.bson.types.ObjectId;

import static java.util.Optional.ofNullable;

import com.example.demo.data.provider.UserManager;

@Service
final class InMemoryUsers implements UserCrudService {

	//Map<String, User> users = new HashMap<>();
	@Autowired
	private UserManager userManager;

	@Override
	public User save(final User user) {
	    if(userManager.addUser(user)) {
	    	return user;
	    }else {
	    	return null;
	    }
	    	
		//return users.put(user.getId(), user);
	}

	@Override
	public Optional<User> find(final ObjectId id) {
		return ofNullable(userManager.findUserById(id));
		//return ofNullable(users.get(id));
	}

	@Override
	public Optional<User> findByUsername(final String usernameInput) {
		return ofNullable(userManager.findUserByUsername(usernameInput));
		//return users.values().stream().filter(u -> Objects.equals(username, u.getUsername())).findFirst();
	}
}
