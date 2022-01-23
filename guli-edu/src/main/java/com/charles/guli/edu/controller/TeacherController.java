package com.charles.guli.edu.controller;

import cn.hutool.core.lang.Dict;
import com.charles.common.domain.PageBean;
import com.charles.common.domain.R;
import com.charles.guli.edu.domain.pojo.Teacher;
import com.charles.guli.edu.domain.vo.TeacherQuery;
import com.charles.guli.edu.service.TeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Api(tags = "教师管理模块")
@RequiredArgsConstructor
@RestController
@RequestMapping("/teachers")
public class TeacherController {

    private final TeacherService teacherService;

    @ApiOperation("查询所有教师列表")
    @GetMapping
    public R findAll() {
        List<Teacher> teachers = teacherService.findAll();
        return R.ok(teachers);
    }

    @ApiOperation("查询所有教师列表（分页）")
    @GetMapping("/{pageNum}/{pageSize}")
    public R findPage(@PathVariable Integer pageNum, @PathVariable Integer pageSize) {
        PageBean<Teacher> teachers = teacherService.findPage(pageNum, pageSize);
        return R.ok(teachers);
    }

    @ApiOperation("条件查询所有教师列表（分页）")
    @PostMapping("/{pageNum}/{pageSize}")
    public R findByCondition(@PathVariable Integer pageNum,
                             @PathVariable Integer pageSize,
                             @RequestBody(required = false) TeacherQuery teacherQuery) {
        PageBean<Teacher> teachers = teacherQuery == null ?
                teacherService.findPage(pageNum, pageSize) :
                teacherService.findByCondition(pageNum, pageSize, teacherQuery);
        return R.ok(teachers);
    }

    @ApiOperation("添加教师")
    @PostMapping
    public R addTeacher(@RequestBody Teacher teacher) {
        Teacher result = teacherService.add(teacher);
        return R.ok(result);
    }

    @ApiOperation( "根据id查询教师")
    @GetMapping("/{id}")
    public R findOne(@PathVariable Integer id) {
        Teacher teacher = teacherService.findById(id);
        return R.ok(teacher);
    }

    @ApiOperation( "根据id更新教师")
    @PutMapping("/{id}")
    public R update(@PathVariable Integer id, @RequestBody Teacher teacher) {
        teacher.setId(id);
        Teacher result = teacherService.update(teacher);
        return R.ok(result);
    }

    @ApiOperation( "根据id删除教师")
    @DeleteMapping("/{id}")
    public R remove(@PathVariable Integer id) {
        teacherService.remove(id);
        return R.ok();
    }

    @ApiOperation( "上传教师头像")
    @PostMapping("/upload/avatar")
    public R uploadAvatar(MultipartFile avatar) throws IOException {
        String url = teacherService.uploadAvatar(avatar);
        return R.ok(Dict.create().set("url",url));
    }
}
