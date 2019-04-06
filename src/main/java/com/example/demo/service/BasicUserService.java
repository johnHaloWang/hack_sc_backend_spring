package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.data.repository.UserRepository2;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Service
public class BasicUserService implements UserService {

    private final UserRepository2 repository;

    @Autowired
    public BasicUserService(final UserRepository2 repository) {
        this.repository = repository;
    }

    @Override
    public User create(final User user) {
        user.setCreatedAt(String.valueOf(LocalDateTime.now()));
        return repository.save(user);
    }

    @Override
    public User find(final ObjectId id) {
        return repository.findBy_id(id);
    }

    @Override
    public User findByUsername(final String userName) {
        return repository.findByUsername(userName);
    }

    @Override
    public List<User> findAll() {
        return repository.findAll();
    }

    @Override
    public User update(final ObjectId id, final User user) {
        user.set_id(id);

        final User saved = repository.findBy_id(id);

        if (saved != null) {
            user.setCreatedAt(saved.getCreatedAt());
            user.setUpdatedAt(String.valueOf(LocalDateTime.now()));
        } else {
            user.setCreatedAt(String.valueOf(LocalDateTime.now()));
        }
        repository.save(user);
        return user;
    }

    @Override
    public String delete(final ObjectId id) {
        repository.deleteById(id.toHexString());
        return id.toHexString();
    }
}
