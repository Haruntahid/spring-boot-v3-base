package com.hmtmcse.v3base.controller;

import com.hmtmcse.v3base.model.entity.Users;
import com.hmtmcse.v3base.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UsersController {

    @Autowired
    private UsersService usersService;

    // create a user
    @PostMapping("/user")
    public ResponseEntity<?> addUser(@RequestBody Users user) {
        boolean isCreated = usersService.save(user);
        if (isCreated) {
            return ResponseEntity.ok("User added successfully");
        }else {
            return ResponseEntity.badRequest().body("User already exists");
        }
    }

    // find all user
    @GetMapping("/user")
    public List<Users> getUser() {
        return usersService.findAllUsers();
    }

    @PutMapping("/user/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id ,@RequestBody Users user) {
        boolean isUpdated = usersService.update(id, user);
        if (isUpdated) {
            return ResponseEntity.ok("User updated successfully");
        }else{
            return ResponseEntity.badRequest().body("User not found");
        }
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        boolean isExist = usersService.delete(id);
        if (!isExist) {
            return ResponseEntity.ok("ID Not Found");
        } else {
            return ResponseEntity.ok("Deleted Successfully");
        }    }



}
