package com.charles.health.repository;

import com.charles.health.domain.entity.CheckItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CheckItemRepository extends JpaRepository<CheckItem, Integer>, JpaSpecificationExecutor<CheckItem> {
}
