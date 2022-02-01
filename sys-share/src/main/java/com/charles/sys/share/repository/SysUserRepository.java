package com.charles.sys.share.repository;

import com.charles.sys.share.domain.pojo.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SysUserRepository extends JpaRepository<SysUser,Integer> {

    SysUser findByUsername(String username);

    SysUser findByUsernameAndPassword(String username,String password);
}
