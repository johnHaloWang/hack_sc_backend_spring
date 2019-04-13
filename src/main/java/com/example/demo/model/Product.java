package com.example.demo.model;


import java.util.Date;
import org.bson.types.ObjectId;

import com.example.demo.model.Geolocation;


/**
 * @author johnhalowang, Lisa Chen
 *
 */
public class Product {
	/** The unique store Id */
    private  ObjectId _id;  
	private String store_id;
    private double price;
    private double gasCost;
    private double totalPrice;
    private String name;
    private String brand;
    private boolean available;
    private String stocked_date;
    private String pictureFileName;
    private Geolocation geolocation;
    /** The timestamp when the store is being created */
    private String creationTime = new Date(System.currentTimeMillis()).toString();
    
    
    public Product() {
    	
    }
    
    public Product(ObjectId _id, String store_id, double price, String name, String brand, boolean available,
			String stocked_date, String pictureFileName, Geolocation geolocation) {
    	
		super();
		this._id = _id;
		this.store_id = store_id;
		this.price = price;
		this.name = name;
		this.brand = brand;
		this.available = available;
		this.stocked_date = stocked_date;
		this.pictureFileName = pictureFileName;
		this.geolocation = geolocation;
		gasCost = 0;
		calcTotalPrice();
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


	/**
	 * @return the store_id
	 */
	public String getStore_id() {
		return store_id;
	}
	

	/**
	 * @param store_id the store_id to set
	 */
	public void setStore_id(String store_id) {
		this.store_id = store_id;
	}

	/**
	 * @return the price
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(double price) {
		this.price = price;
		calcTotalPrice();
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
	 * @return the brand
	 */
	public String getBrand() {
		return brand;
	}

	/**
	 * @param brand the brand to set
	 */
	public void setBrand(String brand) {
		this.brand = brand;
	}

	/**
	 * @return the available
	 */
	public boolean isAvailable() {
		return available;
	}

	/**
	 * @param available the available to set
	 */
	public void setAvailable(boolean available) {
		this.available = available;
	}

	/**
	 * @return the stocked_date
	 */
	public String getStocked_date() {
		return stocked_date;
	}

	/**
	 * @param stocked_date the stocked_date to set
	 */
	public void setStocked_date(String stocked_date) {
		this.stocked_date = stocked_date;
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
	 * @return the _id
	 */
	public String get_id() {
		return _id.toHexString();
	}
	
	/**
	 * @return the _id
	 */
	public ObjectId getObjectID() {
		return _id;
	}
	
	
	/**
	 * @param _id the _id to set
	 */
	public void set_id(ObjectId _id) {
		this._id = _id;
	}
	
	/**
	 * Gets the gas cost. DO NOT store in value in database.
	 * @return
	 */
	public double getGasCost() { 
		return gasCost;
	}
	
	/**
	 * Sets the cost of the gas used to reach the item. DO NOT store in value in database.
	 * @param gasCost
	 */
	public void setGasCost(double gasCost) {
		this.gasCost = gasCost;
		calcTotalPrice();
	}
	
	/**
	 * Gets the total cost: price of the item + cost of gas to reach item
	 */
	public double getTotalCost() {
		return totalPrice;
	}
	
	private void calcTotalPrice() {
		totalPrice = price + gasCost;
	}
	
	
}
