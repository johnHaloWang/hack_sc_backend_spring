package com.example.demo.data.provider.impl;

import java.util.List;

import com.example.demo.data.repository.UserRepository;
import com.example.demo.data.User;
import com.example.demo.data.provider.UserManager;
import com.example.demo.exceptions.UserExistedException;
import com.example.demo.exceptions.UserPasswordMismatchedException;
import com.example.demo.exceptions.UserDoesntExistedException;

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
	public void updateUser(User user) throws UserExistedException{
		
		User find = userRepository.findBy_id(user.get_ObjectId());
		if(user.getUsername().equals(find.getUsername())) {
			userRepository.save(user);
		}else {
			if(this.isUsernameExist(user.getUsername())) {
				throw new UserExistedException("Username already existed, please pick another username!!");	
			}
		}
	}

	@Override
	public void deleteUser(ObjectId id) throws UserDoesntExistedException{
		User find = userRepository.findBy_id(id);
		if(find!=null) {
			userRepository.delete(find);	
		}else {
			throw new UserDoesntExistedException("User doesn't exits!!");
		}
	}

	@Override
	public List<User> listAllUsers() {
		return userRepository.findAll();
	}

	@Override
	public void addUser(User user) throws UserExistedException {
	
		if(!this.isUsernameExist(user.getUsername())) {
		    userRepository.insert(user);
		}else {
			throw new UserExistedException("Username already existed, please pick another username!!");
		}
	}

	@Override
	public User findUserById(ObjectId id) throws UserDoesntExistedException{
		User find =  this.userRepository.findBy_id(id);
		if(find==null) throw new UserDoesntExistedException("User doesn't exits!!");
		return find;
	}

	@Override
	public User findUserByUsernameAndPassword(String username, String password) throws UserPasswordMismatchedException, UserDoesntExistedException{
		User find =  userRepository.findByUsername(username);
		if(find==null) throw new UserDoesntExistedException("User doesn't exits!!");
		if(find.getPassword()!=password) throw new UserPasswordMismatchedException("Wrong password");
		return find;
	}

	@Override
	public boolean isUsernameExist(String username) {
		User find = userRepository.findByUsername(username);	
		return (find==null)?false:true;
	}

	@Override
	public User findUserByUsername(String username) throws UserDoesntExistedException{
		User find =  userRepository.findByUsername(username);
		if(find==null) throw new UserDoesntExistedException("User doesn't exits!!");
		return find;
	}

}


