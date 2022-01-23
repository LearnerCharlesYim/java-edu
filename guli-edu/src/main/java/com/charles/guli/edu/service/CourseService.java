package com.charles.guli.edu.service;

import com.charles.guli.edu.domain.pojo.Course;
import com.charles.guli.edu.domain.pojo.CourseDescription;
import com.charles.guli.edu.domain.vo.CourseInfoVo;
import com.charles.guli.edu.repository.CourseDescriptionRepository;
import com.charles.guli.edu.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;
    private final CourseDescriptionRepository courseDescriptionRepository;

    public void addCourse(CourseInfoVo courseInfoVo) {
        Course course = new Course();
        BeanUtils.copyProperties(courseInfoVo,course);
        Course result = courseRepository.save(course);

        CourseDescription courseDescription = new CourseDescription();
        courseDescription.setDescription(courseInfoVo.getDescription());
        courseDescription.setCourseId(result.getId());
        courseDescriptionRepository.save(courseDescription);
    }
}
