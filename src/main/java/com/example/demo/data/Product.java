package com.example.demo.data;


import java.util.Date;
import org.bson.types.ObjectId;
import com.example.demo.data.Geolocation;


public class Product {
	/** The unique store Id */
    private  ObjectId _id;  
	private String store_id;
    private double price;
    private String name;
    private String brand;
    private boolean available;
    private String stocked_date;
    private String pictureFileName;
    private Geolocation geolocation;
    
    
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
	 * @param _id the _id to set
	 */
	public void set_id(ObjectId _id) {
		this._id = _id;
	}
}
