package com.charles.guli.edu.repository;

import com.charles.guli.edu.domain.pojo.Menu;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MenuRepository extends JpaRepository<Menu, Integer> {

    List<Menu> findByStatus(Boolean status, Sort sort);

    List<Menu> findByParentId(Integer parentId);
}
