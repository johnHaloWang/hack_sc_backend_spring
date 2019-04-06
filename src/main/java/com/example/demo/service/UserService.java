package com.example.demo.service;

import com.example.demo.model.User;
import org.bson.types.ObjectId;
import java.util.List;


public interface UserService {

    User create(User object);

    User find(ObjectId id);

    User findByUsername(String userName);

    List<User> findAll();

    User update(ObjectId id, User object);

    String delete(ObjectId id);
}
