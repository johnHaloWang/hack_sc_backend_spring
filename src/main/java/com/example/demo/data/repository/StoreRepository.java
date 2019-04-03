/**
 * 
 */
package com.example.demo.data.repository;

import java.util.Collection;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.demo.data.Product;
import com.example.demo.data.Store;

/**
 * @author johnwang
 *
 */

public interface StoreRepository extends MongoRepository<Store, String> {

	Store findBy_id(ObjectId _id);
	Collection<Store> findByNameLike(String name);
}
