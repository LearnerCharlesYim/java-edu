package com.charles.health.repository;

import com.charles.health.domain.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PermissionRepository extends JpaRepository<Permission,Integer>, JpaSpecificationExecutor<Permission> {
}
