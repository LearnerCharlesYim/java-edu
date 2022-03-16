package com.charles.health.repository;

import com.charles.health.domain.entity.OrderSetting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface OrderSettingRepository extends JpaRepository<OrderSetting,Integer>, JpaSpecificationExecutor<OrderSetting> {
}
