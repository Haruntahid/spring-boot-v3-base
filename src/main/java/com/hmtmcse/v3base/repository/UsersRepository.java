package com.hmtmcse.v3base.repository;

import com.hmtmcse.v3base.model.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<Users, Long> {
    Optional<Users> findByEmail(String username);
    Optional<Users> findById(Long id);
}
