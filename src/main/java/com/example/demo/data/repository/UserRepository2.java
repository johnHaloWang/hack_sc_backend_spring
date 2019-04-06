package com.example.demo.data.repository;

import com.example.demo.model.User;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository2 extends MongoRepository<User, String> {

    User findByUsername(final String userName);
    User findBy_id(ObjectId _id);
}