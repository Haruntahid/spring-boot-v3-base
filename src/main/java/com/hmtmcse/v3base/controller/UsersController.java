package com.hmtmcse.v3base.controller;

import com.hmtmcse.v3base.model.entity.Users;
import com.hmtmcse.v3base.service.UsersService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UsersController {

    @Autowired
    private UsersService usersService;


    // create a user
    @PostMapping("/register")
    public ResponseEntity<?> addUser(@RequestBody Users user) {
        boolean isCreated = usersService.save(user);
        if (isCreated) {
            return ResponseEntity.ok("User added successfully");
        }else {
            return ResponseEntity.badRequest().body("User already exists");
        }
    }

    // login a user
    @PostMapping("/login")
    public String  login(@RequestBody Users user) {
        return usersService.verify(user);
    }


    // find all user
    //@PreAuthorize("hasRole('ADMIN')")
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
            return ResponseEntity.badRequest().body("Can't updated User");
        }
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        boolean isExist = usersService.delete(id);
        if (!isExist) {
            return ResponseEntity.badRequest().body("ID Not Found");
        } else {
            return ResponseEntity.ok("Deleted Successfully");
        }
    }

    @GetMapping("csrf")
    public CsrfToken csrf(HttpServletRequest request) {
        return (CsrfToken) request.getAttribute("_csrf");
    }



}
