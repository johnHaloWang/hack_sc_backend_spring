package com.example.demo.data.provider;



import org.bson.types.ObjectId;

import java.util.List;


import com.example.demo.user.entity.User;

public interface UserManager {
	public boolean updateUser(User user);
	public boolean deleteUser(ObjectId id);
	public List<User> listAllUsers();
	public boolean addUser(User user);
	public User findUserById(ObjectId id);
	public User findUserByUsernameAndPassword(String username, String password);
	public boolean isUsernameExist(String username);
	public User findUserByUsername(String username);
}
