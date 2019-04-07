package com.example.demo.dto;

import java.io.Serializable;
import java.util.Date;

import org.bson.types.ObjectId;

import com.example.demo.model.Geolocation;;


public class StoreDTO  implements Serializable{
	
	private static final long serialVersionUID = 2L;
	
	private ObjectId _id;
	private String pictureFileName;
	private String address; 
	private String zipcode;
	private String city; 
	private String state; 
	private Geolocation geolocation;

	private String creationTime = new Date(System.currentTimeMillis()).toString();
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

	public String getCreationTime() {
		return creationTime;
	}
	public void setCreationTime(String creationTime) {
		this.creationTime = creationTime;
	}
	private String name;

	public void setGeolocation(Geolocation geolocation) {
		this.geolocation = geolocation;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String get_id() {
		return _id.toHexString();
	}
	public void set_id(ObjectId _id) {
		this._id = _id;
	}
	public Geolocation getGeolocation() {
		return this.geolocation;
	}

}
