package com.example.demo.data.provider;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.example.demo.data.Geolocation;
import com.example.demo.data.Product;
import com.example.demo.data.repository.StoreRepository;
import com.example.demo.data.repository.UserRepository;
import com.example.demo.user.entity.User;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author johnhalowang
 *
 */
public class FS_UserManager implements UserManager{

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public boolean updateUser(User user) {
		User find = userRepository.findBy_id(user.get_ObjectId());
		if(user.getUsername().equals(find.getUsername())) {
			userRepository.save(user);
			return true;
		}else {
			if(!this.isUsernameExist(user.getUsername())) {
				userRepository.save(user);
				return true;
			}else {
				return false;
			}
		}
	}

	@Override
	public boolean deleteUser(ObjectId id) {
		User find = userRepository.findBy_id(id);
		if(find!=null) {
			userRepository.delete(find);
			return true;
		}else {
			return false;
		}
	}

	@Override
	public List<User> listAllUsers() {
		return userRepository.findAll();
	}

	@Override
	public boolean addUser(User user) {
		if(!this.isUsernameExist(user.getUsername())) {
			userRepository.insert(user);
			return true;
		}else {
			return false;
		}
	}

	@Override
	public User findUserById(ObjectId id) {
		return this.userRepository.findBy_id(id);
	}

	@Override
	public User findUserByUsernameAndPassword(String username, String password) {
		return this.findUserByUsernameAndPassword(username, password);
	}

	@Override
	public boolean isUsernameExist(String username) {
		User find = userRepository.findByUsername(username);
		return (find==null)?false:true;
	}

	@Override
	public User findUserByUsername(String username) {
		return userRepository.findByUsername(username);
	}

}
