package com.charles.guli.edu.repository;

import com.charles.guli.edu.domain.pojo.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRoleRepository extends JpaRepository<UserRole, Integer> {

    @Query("select ur.roleId from UserRole ur where ur.userId = ?1")
    List<Integer> findRoleIdsByUserId(int loginIdAsInt);
}
