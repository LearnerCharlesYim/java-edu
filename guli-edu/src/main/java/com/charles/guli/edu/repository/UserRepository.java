package com.charles.guli.edu.repository;

import com.charles.guli.edu.domain.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsername(String username);
}
