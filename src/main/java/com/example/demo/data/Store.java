package com.example.demo.data;

import java.util.Date;

/**
 * The basic Store object.
 * @author johnhalowang (chi-wei wang)
 */
public class Store {
	/** The unique store Id */
    private String id;   
	/** The store name */
    private String name;
    
    /** The timestamp when the user is being created */
    private String creationTime = new Date(System.currentTimeMillis()).toString();
    /** The file name for the picture */
    private String pictureFileName;
    /** The store gelocation */
    private Geolocation location;
    
    
    /**
	 * @return the location
	 */
	public Geolocation getLocation() {
		return location;
	}

	/**
	 * @param location the location to set
	 */
	public void setLocation(Geolocation location) {
		this.location = location;
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
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
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
