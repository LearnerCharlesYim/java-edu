package com.charles.guli.edu.repository;

import com.charles.guli.edu.domain.pojo.RoleMenu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;


public interface RoleMenuRepository extends JpaRepository<RoleMenu, Integer> {

    @Transactional
    void deleteAllByRoleId(Integer roleId);

    @Query("select rm.menuId from RoleMenu rm where rm.roleId = ?1")
    List<Integer> findCustomMenuIds(Integer roleId);
}
