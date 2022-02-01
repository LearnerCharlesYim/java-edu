package com.charles.guli.edu.repository;

import com.charles.guli.edu.domain.pojo.Menu;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Set;

public interface MenuRepository extends JpaRepository<Menu, Integer> {

    List<Menu> findByStatus(Boolean status, Sort sort);

    List<Menu> findByParentId(Integer parentId);

    @Query("select m.path from Menu m where m.id in ?1")
    List<String> findPath(Set<Integer> menuIds);

    @Query("select m.path from Menu m where m.path is not null")
    List<String> findAllPaths();

    List<Menu> findByType(Menu.Type button);
}
