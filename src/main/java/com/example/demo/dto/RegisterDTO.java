package com.example.demo.dto;

import java.io.Serializable;
import java.util.Date;
import com.example.demo.dto.StoreDTO;
import org.bson.types.ObjectId;


public class RegisterDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private ObjectId _id;
	public String get_id() {
		return _id.toHexString();
	}
	public void set_id(ObjectId _id) {
		this._id = _id;
	}
	private String username;
	private String password;
	
	private String store_id;
	private String contactNumber;
	private String role;
	private String email;
	private StoreDTO store;

	private String creationTime = new Date(System.currentTimeMillis()).toString();
	
	public String getUsername() {
		return username;
	}
	public void setUsername(final String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(final String password) {
		this.password = password;
	}
	public String getStore_id() {
		return store_id;
	}
	public void setStore_id(String store_id) {
		this.store_id = store_id;
	}
	public String getContactNumber() {
		return contactNumber;
	}
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public StoreDTO getStore() {
		return store;
	}
	public void setStore(StoreDTO store) {
		this.store = store;
	}
	public String getCreationTime() {
		return creationTime;
	}
	public void setCreationTime(String creationTime) {
		this.creationTime = creationTime;
	}

	
}
