package com.charles.health.repository;

import com.charles.health.domain.entity.CheckGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CheckGroupRepository extends JpaRepository<CheckGroup,Integer>, JpaSpecificationExecutor<CheckGroup> {
}
