package com.example.demo.user.crud.api;

import com.example.demo.user.entity.User;

import java.util.Optional;
import org.bson.types.ObjectId;
/**
 * User security operations like login and logout, and CRUD operations on
 * {@link User}.
 * 
 * @author johnhalowang
 *
 */
public interface UserCrudService {

	User save(User user);
	Optional<User> find(ObjectId id);
	Optional<User> findByUsername(String username);
}
