package com.example.demo.dto;

import java.io.Serializable;

import org.bson.types.ObjectId;

public class IndexDTO implements Serializable{
	
	private static final long serialVersionUID = 4L;

    private ObjectId _id;

	public ObjectId get_id() {
		return _id;
	}

	public void set_id(ObjectId _id) {
		this._id = _id;
	}
}
