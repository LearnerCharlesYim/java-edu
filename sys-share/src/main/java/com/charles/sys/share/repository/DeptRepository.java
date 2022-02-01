package com.charles.sys.share.repository;

import com.charles.sys.share.domain.pojo.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeptRepository extends JpaRepository<Department,Integer> {

}
