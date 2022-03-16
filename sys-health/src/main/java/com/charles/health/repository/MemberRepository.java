package com.charles.health.repository;

import com.charles.health.domain.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface MemberRepository extends JpaRepository<Member,Integer>, JpaSpecificationExecutor<Member> {
}
