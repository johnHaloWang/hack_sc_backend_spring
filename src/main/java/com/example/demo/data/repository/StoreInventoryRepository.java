package com.example.demo.data.repository;

import java.util.List;
import java.util.Collection;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.example.demo.model.Product;


/**
 * @author johnhalowang, Lisa Chen, Gina 
 *
 */
public interface StoreInventoryRepository extends MongoRepository<Product, String> {

	Product findBy_id(ObjectId _id);
	Collection<Product> findByNameLike(String name);
}
