package com.hmtmcse.v3base.repository;

import com.hmtmcse.v3base.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Users, Long> {
}