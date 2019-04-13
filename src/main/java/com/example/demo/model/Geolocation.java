/**
 * 
 */
package com.example.demo.model;

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
	
	@Override
	public boolean equals(Object o)
	{
		if (!(o instanceof Geolocation))
			return false;
		Geolocation comparator = (Geolocation) o;
		double latitudeDiff = Math.abs(this.latitude - comparator.latitude);
		double longitudeDiff = Math.abs(this.longitude - comparator.longitude);
		boolean latitudeCheck = latitudeDiff == 0 || latitudeDiff < Float.MIN_NORMAL;
		boolean longitudeCheck = longitudeDiff == 0 || longitudeDiff < Float.MIN_NORMAL;	
		return latitudeCheck && longitudeCheck;
	}

}
