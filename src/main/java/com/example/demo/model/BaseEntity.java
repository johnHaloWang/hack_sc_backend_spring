package com.example.demo.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;


@Document
public class BaseEntity implements Serializable {

    private static final long serialVersionUID = 8571261118900116242L;

    @Id
    private ObjectId _id;
    private String createdAt;
    private String updatedAt;

    public BaseEntity() {
    }

    public String get_id() {
        return _id.toHexString();
    }

    public void set_id(final ObjectId id) {
        this._id = id;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(final String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(final String updatedAt) {
        this.updatedAt = updatedAt;
    }
}
