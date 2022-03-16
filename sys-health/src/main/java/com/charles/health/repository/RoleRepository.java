package com.charles.health.repository;

import com.charles.health.domain.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface RoleRepository extends JpaRepository<Role,Integer>, JpaSpecificationExecutor<Role> {
}
