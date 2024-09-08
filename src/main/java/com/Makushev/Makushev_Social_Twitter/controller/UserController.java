package com.Makushev.Makushev_Social_Twitter.controller;

import com.Makushev.Makushev_Social_Twitter.Repository.UserRepository;
import com.Makushev.Makushev_Social_Twitter.exceptions.UserException;
import com.Makushev.Makushev_Social_Twitter.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.Makushev.Makushev_Social_Twitter.service.UserService;

import java.util.List;

@RestController
@ResponseBody
public class UserController {

    @GetMapping("/api/users")
    public List<User> getUsers() {

        List<User> users = userRepository.findAll();

        return users;
    }

    @PostMapping("/users")
    public User createUser(@RequestBody User user) {

        User savedUser = userService.registerUser(user);

        return savedUser;
    }

    @GetMapping("/api/users/{userId}")
    public User getUserById(@PathVariable("userId") Long id) throws UserException {

        User user = userService.findUserById(id);

        return user;
    }

    @PutMapping("/api/users/{userId}")
    public User updateUser(@RequestHeader("Authorization") String jwt, @RequestBody User user) throws UserException {

        User reqUser = userService.findUserByJwt(jwt);

        User updatedUser = userService.updateUser(user, reqUser.getId()); // что бы другие не обновляли, а только ты

        return updatedUser;

    }

    @PutMapping("/api/users/follow/{userId2}")
    public User followUserHandler(@RequestHeader("Authorization") String jwt, @PathVariable Long userId2) throws UserException {

        User reqUser = userService.findUserByJwt(jwt);

        User user = userService.followUser(reqUser.getId(), userId2);

        return user;
    }

    @GetMapping("/api/users/search")
    public List<User> searchUser(@RequestParam("query") String query){

        List<User> users = userService.searchUser(query);

        return users;
    }
    @GetMapping("/api/users/profile")
    public User getUserFromToken(@RequestHeader("Authorization") String jwt) {

//        String email =
//        System.out.println("jwt ----- " + jwt);

        User user = userService.findUserByJwt(jwt);

        user.setPassword(null);

        return user;   // выдаем по токену всю инфу про чела
    }







    /**
     * AutoWired
     */

    UserRepository userRepository;
    UserService userService;

    @Autowired
    public UserController(UserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }
}
