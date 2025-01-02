package com.hmtmcse.v3base.service;

import com.hmtmcse.v3base.model.entity.Users;
import com.hmtmcse.v3base.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsersService {

    @Autowired
    private UsersRepository usersRepository;

    // create a user
    public boolean save(Users user) {
        if(usersRepository.findByEmail(user.getEmail()).isPresent()) {
            return false;
        }else {
             usersRepository.save(user);
            return true;
        }
    }

    // get all user
    public List<Users> findAllUsers() {
        return usersRepository.findAll();
    }

    //update a user
    public boolean update(Long id,Users user) {
        boolean idExist = usersRepository.findById(id).isPresent();
        if(idExist) {
            Users existingUser = usersRepository.findById(id).get();

            if(!existingUser.getEmail().equals(user.getEmail())) {
                return false;
            }else{
                // existingUser.setEmail(user.getEmail());
                existingUser.setFullName(user.getFullName());
                existingUser.setPassword(user.getPassword());
                usersRepository.save(existingUser);
                return true;
            }
        }else{
            return false;
        }
    }

    //delete a user
    public boolean delete(Long id) {
        boolean idExist = usersRepository.findById(id).isPresent();

        if (idExist) {
            usersRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }


}
