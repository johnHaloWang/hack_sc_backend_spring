package com.example.demo.model;


import java.util.Date;
import org.bson.types.ObjectId;


/**
 * The basic Store object.
 * @author johnhalowang (chi-wei wang)
 */

public class Store {
	
	private  ObjectId _id;  
	private String name;
    private String creationTime = new Date(System.currentTimeMillis()).toString();
    private String pictureFileName;
    private Geolocation geolocation;
	private String address;
    private String zipcode;
    private String city;
    private String state;
    
    
    public Store() {
    	
    }
    
	public Store(ObjectId _id, String name, String pictureFileName, Geolocation geolocation, String address,
			String zipcode, String city, String state) {
		super();
		this._id = _id;
		this.name = name;
		this.pictureFileName = pictureFileName;
		this.geolocation = geolocation;
		this.address = address;
		this.zipcode = zipcode;
		this.city = city;
		this.state = state;
	}

	/**
	 * @return the geolocation
	 */
	public Geolocation getGeolocation() {
		return geolocation;
	}

	/**
	 * @param geolocation the geolocation to set
	 */
	public void setGeolocation(Geolocation geolocation) {
		this.geolocation = geolocation;
	}


      
    /**
	 * @return the state
	 */
	public String getState() {
		return state;
	}

	/**
	 * @param state the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}

//	public String getStoreAddress() {
//    	String buildAddress =  address + ' ' + city + ' ' + zipcode + ", "+ state; 
//    	return buildAddress;
//    }
    
    
    /**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the zipcode
	 */
	public String getZipcode() {
		return zipcode;
	}

	/**
	 * @param zipcode the zipcode to set
	 */
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}
	
	/**
	 * @return the pictureFileName
	 */
	public String getPictureFileName() {
		return pictureFileName;
	}

	/**
	 * @param pictureFileName the pictureFileName to set
	 */
	public void setPictureFileName(String pictureFileName) {
		this.pictureFileName = pictureFileName;
	}

	/**
	 * @return the _id
	 */
	public String get_id() {
		return _id.toHexString();
	}
	
	/**
	 * @param _id the _id to set
	 */
	public void set_id(ObjectId _id) {
		this._id = _id;
	}
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * @return the creationTime
	 */
	public String getCreationTime() {
		return creationTime;
	}
	
	/**
	 * @param creationTime the creationTime to set
	 */
	public void setCreationTime(String creationTime) {
		this.creationTime = creationTime;
	}
	
	
}
