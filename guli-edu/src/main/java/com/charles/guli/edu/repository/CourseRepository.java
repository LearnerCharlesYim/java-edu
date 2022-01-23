package com.charles.guli.edu.repository;

import com.charles.guli.edu.domain.pojo.Course;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Integer> {
}
