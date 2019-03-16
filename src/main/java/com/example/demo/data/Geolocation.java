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
    private float latitude;
	private float longitude;
	
	
	
	/**
	 * @return the latitude
	 */
	public float getLatitude() {
		return latitude;
	}
	/**
	 * @param latitude the latitude to set
	 */
	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}
	/**
	 * @return the longitude
	 */
	public float getLongitude() {
		return longitude;
	}
	/**
	 * @param longitude the longitude to set
	 */
	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}

}
