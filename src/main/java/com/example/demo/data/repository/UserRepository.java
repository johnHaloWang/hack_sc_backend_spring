package com.example.demo.data.repository;


import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.demo.user.entity.User;;

public interface UserRepository extends MongoRepository<User, String> {
	User findBy_id(ObjectId _id);
	User findByUsernameAndPassword(String username, String password);
	User findByUsername(String username);
}
