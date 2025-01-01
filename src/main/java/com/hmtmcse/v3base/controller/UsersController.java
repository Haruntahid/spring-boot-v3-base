package com.hmtmcse.v3base.controller;

import com.hmtmcse.v3base.model.entity.Users;
import com.hmtmcse.v3base.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UsersController {

    @Autowired
    private UsersService usersService;

    // create a user
    @PostMapping("/user")
    public Users addUser(@RequestBody Users user) {
        return usersService.save(user);
    }

    @GetMapping("/user")
    public List<Users> getUser() {
        return usersService.findAllUsers();
    }

}
