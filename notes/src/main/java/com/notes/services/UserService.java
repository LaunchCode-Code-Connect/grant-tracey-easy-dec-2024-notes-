package com.notes.services;

import com.notes.entities.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    //Create
    User createUser(User user);
    //Read
    List<User> fetchUserList();
    Optional<User> findUserById(int userId);
    //Update
    User updateUser(User user, Integer userId);
    //Delete
    void deleteUserById(Integer userId);
}
