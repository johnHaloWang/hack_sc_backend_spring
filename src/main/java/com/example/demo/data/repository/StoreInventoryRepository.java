package com.example.demo.data.repository;

import java.util.Collection;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.demo.data.Product;
import com.example.demo.data.Geolocation;

/**
 * @author johnhalowang, Lisa Cheng, Gina 
 *
 */
public interface StoreInventoryRepository extends MongoRepository<Product, String> {

	Product findBy_id(ObjectId _id);
	Collection<Product> findByNameLike(String name);

}
