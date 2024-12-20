package com.notes.services;

import com.notes.entities.User;
import com.notes.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


    public User createUser(User user) {
        return userRepository.save(user);
    }


    public List<User> fetchUserList() {
        return (List<User>) userRepository.findAll();
    }


    public Optional<User> findUserById(int userId) {
        return userRepository.findById(userId);
    }


    public User updateUser(User user, Integer userId)
    {
        User userDB
                = userRepository.findById(userId).get();

        if (Objects.nonNull(user.getUsername())
            && !"".equalsIgnoreCase(
                    user.getUsername())) {
        userDB.setUsername(
                user.getUsername());
        }
        if (Objects.nonNull(user.getPassword())
                && !"".equalsIgnoreCase(
                user.getPassword())) {
            userDB.setPassword(
                    user.getPassword());
        }

        return userRepository.save(userDB);
    }


    public void deleteUserById(Integer userId) {
        userRepository.deleteById(userId);
    }

    
}
