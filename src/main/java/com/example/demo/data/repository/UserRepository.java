package com.example.demo.data.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import com.example.demo.data.User;;

public interface UserRepository extends MongoRepository<User, String> {
	public User findBy_id(ObjectId _id);
	public User findByUsernameAndPassword(String username, String password);
	public User findByUsername(String username);
}
