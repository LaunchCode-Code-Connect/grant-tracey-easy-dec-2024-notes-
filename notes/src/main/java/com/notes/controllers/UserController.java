package com.notes.controllers;

import com.notes.entities.User;
import com.notes.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


//@RestController annotation combines @Controller and @ResponseBody
@RequestMapping("/user")
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    //Create
    @PostMapping("")
    public User createUser(@RequestBody User user)
    {
        return userService.createUser(user);
    }

    //Read
    @GetMapping("")
    public List<User> fetchUserList()
    {
        return userService.fetchUserList();
    }

    @GetMapping("/{id}")
    public Optional<User> findUserById(@PathVariable("id")
                                           Integer userId)
    {
        return userService.findUserById(userId);
    }

    //Update
    @PutMapping("/{id}")
    public User
    updateUser(@RequestBody User user,
               @PathVariable("id") Integer userId)
    {
        return userService.updateUser(
                user, userId
        );
    }

    //Delete
    @DeleteMapping("/{id}")
    public String deleteUserById(@PathVariable("id")
                                 Integer userId)
    {
        userService.deleteUserById(
                userId);
        return "Deleted Successfully";
    }
}
