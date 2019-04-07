package com.example.demo.dto;


import java.io.Serializable;
import com.example.demo.model.Geolocation;

public class GeoRequestDTO implements Serializable{
	
	private static final long serialVersionUID = 5L;
	private String name;
	private Geolocation geolocation;
	private float radius;
	private float mpg;
	
	public float getRadius() {
		return radius;
	}
	public void setRadius(float radius) {
		this.radius = radius;
	}
	public float getMpg() {
		return mpg;
	}
	public void setMpg(float mpg) {
		this.mpg = mpg;
	}
	public Geolocation getGeolocation() {
		return geolocation;
	}
	public void setGeolocation(Geolocation geolocation) {
		this.geolocation = geolocation;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

}
