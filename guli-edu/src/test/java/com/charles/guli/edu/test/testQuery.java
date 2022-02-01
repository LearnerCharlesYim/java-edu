package com.charles.guli.edu.test;

import cn.hutool.core.date.DateUtil;
import com.charles.common.utils.QueryHelp;
import com.charles.guli.edu.domain.pojo.Course;
import com.charles.guli.edu.domain.vo.CourseQuery;
import com.charles.guli.edu.domain.vo.TeacherQuery;
import com.charles.guli.edu.repository.CourseRepository;
import com.charles.guli.edu.repository.TeacherRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class testQuery {

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private CourseRepository courseRepository;


    @Test
    public void test1(){
        TeacherQuery teacher = new TeacherQuery();
        teacher.setBegin(DateUtil.parse("2022-01-10"));
        teacher.setEnd(DateUtil.parse("2022-01-31"));
//        teacher.setName("胡");
        teacher.setLevel(2);
        System.out.println(teacherRepository.findAll((root, query, builder) -> QueryHelp.getPredicate(root, teacher, builder)));

    }

    @Test
    public void test2(){
        CourseQuery q = new CourseQuery();

        System.out.println(courseRepository.findAll((root, query, builder) -> QueryHelp.getPredicate(root, q, builder)));
    }
}
