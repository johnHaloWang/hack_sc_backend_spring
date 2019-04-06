package com.example.demo.data.provider.impl;

import java.util.List;

import com.example.demo.data.repository.UserRepository;
import com.example.demo.data.AppUser;
import com.example.demo.data.provider.UserManager;
import com.example.demo.exceptions.UserExistedException;
import com.example.demo.exceptions.UserPasswordMismatchedException;
import com.example.demo.exceptions.UserDoesntExistException;

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
	public void updateUser(AppUser user) throws UserExistedException{
		
		AppUser find = userRepository.findBy_id(user.get_ObjectId());
		if(user.getUsername().equals(find.getUsername())) {
			userRepository.save(user);
		}else {
			if(this.isUsernameExist(user.getUsername())) {
				throw new UserExistedException("Username already exists, please pick another username!");	
			}
		}
	}

	@Override
	public void deleteUser(ObjectId id) throws UserDoesntExistException{
		AppUser find = userRepository.findBy_id(id);
		if(find!=null) {
			userRepository.delete(find);	
		}else {
			throw new UserDoesntExistException("User doesn't exist!");
		}
	}

	@Override
	public List<AppUser> listAllUsers() {
		return userRepository.findAll();
	}

	@Override
	public void addUser(AppUser user) throws UserExistedException {
	
		if(!this.isUsernameExist(user.getUsername())) {
		    userRepository.insert(user);
		}else {
			throw new UserExistedException("Username already exists, please pick another username!!");
		}
	}

	@Override
	public AppUser findUserById(ObjectId id) throws UserDoesntExistException{
		AppUser find =  this.userRepository.findBy_id(id);
		if(find==null) throw new UserDoesntExistException("User doesn't exist!");
		return find;
	}

	@Override
	public AppUser findUserByUsernameAndPassword(String username, String password) throws UserPasswordMismatchedException, UserDoesntExistException{
		AppUser find =  userRepository.findByUsername(username);
		if(find==null) throw new UserDoesntExistException("User doesn't exist!");
		if(find.getPassword()!=password) throw new UserPasswordMismatchedException("Wrong password");
		return find;
	}

	@Override
	public boolean isUsernameExist(String username) {
		AppUser find = userRepository.findByUsername(username);	
		return (find==null)?false:true;
	}

	@Override
	public AppUser findUserByUsername(String username) throws UserDoesntExistException{
		AppUser find =  userRepository.findByUsername(username);
		if(find==null) throw new UserDoesntExistException("User doesn't exist!");
		return find;
	}

}


