package com.example.demo.data.provider;

import org.bson.types.ObjectId;

import java.util.List;


import com.example.demo.data.User;
import com.example.demo.exceptions.UserExistedException;
import com.example.demo.exceptions.UserDoesntExistedException;
import com.example.demo.exceptions.UserPasswordMismatchedException;

public interface UserManager {
	public void updateUser(User user) throws UserExistedException;
	public void deleteUser(ObjectId id) throws UserDoesntExistedException;
	public List<User> listAllUsers();
	public void addUser(User user) throws UserExistedException;
	public User findUserById(ObjectId id) throws UserDoesntExistedException;
	public User findUserByUsernameAndPassword(String username, String password) throws UserPasswordMismatchedException, UserDoesntExistedException;
	public boolean isUsernameExist(String username);
	public User findUserByUsername(String username) throws UserDoesntExistedException;
}
