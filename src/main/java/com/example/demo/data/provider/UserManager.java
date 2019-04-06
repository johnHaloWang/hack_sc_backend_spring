package com.example.demo.data.provider;

import org.bson.types.ObjectId;

import java.util.List;

import com.example.demo.data.AppUser;
import com.example.demo.exceptions.UserExistedException;
import com.example.demo.exceptions.UserDoesntExistException;
import com.example.demo.exceptions.UserPasswordMismatchedException;

public interface UserManager {
	public void updateUser(AppUser user) throws UserExistedException;
	public void deleteUser(ObjectId id) throws UserDoesntExistException;
	public List<AppUser> listAllUsers();
	public void addUser(AppUser user) throws UserExistedException;
	public AppUser findUserById(ObjectId id) throws UserDoesntExistException;
	public AppUser findUserByUsernameAndPassword(String username, String password) throws UserPasswordMismatchedException, UserDoesntExistException;
	public boolean isUsernameExist(String username);
	public AppUser findUserByUsername(String username) throws UserDoesntExistException;
}
