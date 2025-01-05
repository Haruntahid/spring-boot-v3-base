package com.hmtmcse.v3base.repository;

import com.hmtmcse.v3base.model.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {
    Optional<Users> findByEmail(String email);
    Optional<Users> findById(Long id);
    Users findByFullName(String fullName);
}
