/**
 * 
 */
package com.example.demo.data;

/**
 * The basic geolocation object
 * @author johnhalowang (chi-wei wang)
 *
 */
public class Geolocation {
	
	/** The geolocation */
    private double latitude;
	private double longitude;
	
	
	
	/**
	 * @return the latitude
	 */
	public double getLatitude() {
		return latitude;
	}
	/**
	 * @param latitude the latitude to set
	 */
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	/**
	 * @return the longitude
	 */
	public double getLongitude() {
		return longitude;
	}
	/**
	 * @param longitude the longitude to set
	 */
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

}
