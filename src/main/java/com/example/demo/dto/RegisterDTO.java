package com.example.demo.dto;

import java.io.Serializable;
import java.util.Date;

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
	private String name;
	private String pictureFileName;
	private String address; 
	private String zipcode;
	private String city; 
	private String state; 
	private float latitude; 
	private float longitude;
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
	public String getCreationTime() {
		return creationTime;
	}
	public void setCreationTime(String creationTime) {
		this.creationTime = creationTime;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPictureFileName() {
		return pictureFileName;
	}
	public void setPictureFileName(String pictureFileName) {
		this.pictureFileName = pictureFileName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public float getLatitude() {
		return latitude;
	}
	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}
	public float getLongitude() {
		return longitude;
	}
	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}

	
}
