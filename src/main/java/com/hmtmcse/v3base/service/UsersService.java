package com.hmtmcse.v3base.service;

import com.hmtmcse.v3base.model.entity.Role;
import com.hmtmcse.v3base.model.entity.UserPrincipal;
import com.hmtmcse.v3base.model.entity.Users;
import com.hmtmcse.v3base.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UsersService implements UserDetailsService {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    @Lazy
    AuthenticationManager authManager;

    @Autowired
    private JWTService jwtService;


    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10);

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Users user = usersRepository.findByFullName(username);

        if (user == null) {
            System.out.println("user not found");
            throw new UsernameNotFoundException("user not found");
        }
        return new UserPrincipal(user);
    }


    // create a user
    public boolean save(Users user) {
        if (usersRepository.findByEmail(user.getEmail()).isPresent()) {
            return false;
        } else {
            user.setPassword(encoder.encode(user.getPassword()));
//            List getRoles = user.getRoles();

//            System.out.println("roles : " + getRoles);
//
//            for (Object role : getRoles) {
//                System.out.println("role : " + role);
//            }

            usersRepository.save(user);
            return true;
        }
    }

    // get all user
    public List<Users> findAllUsers() {
        return usersRepository.findAll();
    }

    //update a user
    public boolean update(Long id, Users user) {
        boolean idExist = usersRepository.findById(id).isPresent();
        if (idExist) {
            Users existingUser = usersRepository.findById(id).get();

            if (!existingUser.getEmail().equals(user.getEmail())) {
                return false;
            } else {
                // existingUser.setEmail(user.getEmail());
                existingUser.setFullName(user.getFullName());
                existingUser.setPassword(user.getPassword());
                usersRepository.save(existingUser);
                return true;
            }
        } else {
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


    public String verify(Users user) {
        Authentication authentication = authManager.authenticate(new UsernamePasswordAuthenticationToken(user.getFullName(), user.getPassword()));
        if (authentication.isAuthenticated()){
            return jwtService.generateToken(user.getFullName());
        }

        return "Login Failed";
    }

}
