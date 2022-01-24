package com.charles.guli.edu.controller;

import com.charles.common.domain.R;
import com.charles.guli.edu.domain.vo.CourseInfoVo;
import com.charles.guli.edu.service.CourseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "课程管理模块")
@RequiredArgsConstructor
@RestController
@RequestMapping("/course")
public class CourseController {

    private final CourseService courseService;

    @ApiOperation("课程添加")
    @PostMapping
    public R<Void> addCourse(@RequestBody CourseInfoVo courseInfoVo){
        courseService.addCourse(courseInfoVo);
        return R.ok();
    }
}
