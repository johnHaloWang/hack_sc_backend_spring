package com.example.demo.data.repository;

import java.util.Collection;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.demo.model.Product;


/**
 * @author johnhalowang, Lisa Cheng, Gina 
 *
 */
public interface StoreInventoryRepository extends MongoRepository<Product, String> {

	Product findBy_id(ObjectId _id);
	Collection<Product> findByNameLike(String name);


}
