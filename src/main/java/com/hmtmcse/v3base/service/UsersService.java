package com.hmtmcse.v3base.service;

import com.hmtmcse.v3base.model.entity.Users;
import com.hmtmcse.v3base.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersService {

    @Autowired
    private UsersRepository usersRepository;

    // create a user
    public Users save(Users user) {
        return usersRepository.save(user);
    }

    // get all user
    public List<Users> findAllUsers() {
        return usersRepository.findAll();
    }

}
