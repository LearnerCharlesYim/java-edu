package com.charles.guli.edu.repository;

import com.charles.guli.edu.domain.dto.RoleDto;
import com.charles.guli.edu.domain.pojo.Role;
import com.charles.guli.edu.domain.vo.RoleQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RoleRepository extends JpaRepository<Role, Integer> {

    @Query("select new com.charles.guli.edu.domain.dto.RoleDto(r.id, r.roleName, r.roleCode, r.sort,r.status, r.createdTime) " +
            "from Role r where" +
            "(:#{#roleQuery.roleName} is null or r.roleName like %:#{#roleQuery.roleName}%) and " +
            "(:#{#roleQuery.roleCode} is null or r.roleCode=:#{#roleQuery.roleCode}) and " +
            "(:#{#roleQuery.begin} is null or r.createdTime >:#{#roleQuery.begin}) and " +
            "(:#{#roleQuery.end} is null or r.createdTime <:#{#roleQuery.end})")
    Page<RoleDto> findCustom(RoleQuery roleQuery, Pageable pageable);
}
