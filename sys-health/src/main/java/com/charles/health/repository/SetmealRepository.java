package com.charles.health.repository;

import com.charles.health.domain.entity.Setmeal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface SetmealRepository extends JpaRepository<Setmeal,Integer>, JpaSpecificationExecutor<Setmeal> {
}
