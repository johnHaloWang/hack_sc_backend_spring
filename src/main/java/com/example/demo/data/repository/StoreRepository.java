/**
 * 
 */
package com.example.demo.data.repository;

import java.util.Collection;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.demo.model.Store;

/**
 * @author johnwang
 *
 */

public interface StoreRepository extends MongoRepository<Store, String> {

	Store findBy_id(ObjectId _id);
	Collection<Store> findByNameLike(String name);
}
