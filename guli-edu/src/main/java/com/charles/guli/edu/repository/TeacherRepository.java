package com.charles.guli.edu.repository;

import com.charles.guli.edu.domain.pojo.Teacher;
import com.charles.guli.edu.domain.vo.TeacherQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TeacherRepository extends JpaRepository<Teacher, Integer> {

    @Query("select t from Teacher t where" +
            "(:#{#teacherQuery.name} is null or t.name like %:#{#teacherQuery.name}%) and " +
            "(:#{#teacherQuery.level} is null or t.level=:#{#teacherQuery.level}) and " +
            "(:#{#teacherQuery.begin} is null or t.createdTime >:#{#teacherQuery.begin}) and " +
            "(:#{#teacherQuery.end} is null or t.createdTime <:#{#teacherQuery.end})")
    Page<Teacher> findCustom(TeacherQuery teacherQuery, Pageable pageable);
}
