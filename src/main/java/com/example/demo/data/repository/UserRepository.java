package com.example.demo.data.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import com.example.demo.data.AppUser;

public interface UserRepository extends MongoRepository<AppUser, String> {
	public AppUser findBy_id(ObjectId _id);
	public AppUser findByUsernameAndPassword(String username, String password);
	public AppUser findByUsername(String username);
}
