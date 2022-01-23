package com.charles.guli.edu.repository;

import com.charles.guli.edu.domain.pojo.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubjectRepository extends JpaRepository<Subject, Integer> {
    Subject findByTitleAndParentId(String title, Integer parentId);

    List<Subject> findByParentId(Integer parentId);
}
