package com.example.demo.dto;

import java.io.Serializable;
import java.util.Date;

import org.bson.types.ObjectId;

import com.example.demo.model.Geolocation;

public class ProductDTO implements Serializable {

    private static final long serialVersionUID = -4L;
	 	private  ObjectId _id;  
		private String store_id;
	    private double price;
//	    private double gasCost;
	    private double totalPrice;
	    private String name;
	    private String brand;
	    private boolean available;
	    private String stocked_date;
	    private String pictureFileName;
	    private Geolocation geolocation;
	    private String creationTime = new Date(System.currentTimeMillis()).toString();
	    
	    public String get_id() {
			return _id.toHexString();
		}
		public void set_id(ObjectId _id) {
			this._id = _id;
		}
		public String getStore_id() {
			return store_id;
		}
		public void setStore_id(String store_id) {
			this.store_id = store_id;
		}
		public double getPrice() {
			return price;
		}
		public void setPrice(double price) {
			this.price = price;
		}
//		public double getGasCost() {
//			return gasCost;
//		}
//		public void setGasCost(double gasCost) {
//			this.gasCost = gasCost;
//		}
		public double getTotalPrice() {
			return totalPrice;
		}
		public void setTotalPrice(double totalPrice) {
			this.totalPrice = totalPrice;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getBrand() {
			return brand;
		}
		public void setBrand(String brand) {
			this.brand = brand;
		}
		public boolean isAvailable() {
			return available;
		}
		public void setAvailable(boolean available) {
			this.available = available;
		}
		public String getStocked_date() {
			return stocked_date;
		}
		public void setStocked_date(String stocked_date) {
			this.stocked_date = stocked_date;
		}
		public String getPictureFileName() {
			return pictureFileName;
		}
		public void setPictureFileName(String pictureFileName) {
			this.pictureFileName = pictureFileName;
		}
		public Geolocation getGeolocation() {
			return geolocation;
		}
		public void setGeolocation(Geolocation geolocation) {
			this.geolocation = geolocation;
		}
		public String getCreationTime() {
			return creationTime;
		}
		public void setCreationTime(String creationTime) {
			this.creationTime = creationTime;
		}
}
