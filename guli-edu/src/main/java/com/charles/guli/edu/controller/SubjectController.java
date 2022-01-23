package com.charles.guli.edu.controller;

import com.charles.common.domain.R;
import com.charles.guli.edu.domain.dto.SubjectTree;
import com.charles.guli.edu.domain.pojo.Subject;
import com.charles.guli.edu.service.SubjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Api(tags = "课程分类管理")
@RequiredArgsConstructor
@RestController
@RequestMapping("/subjects")
public class SubjectController {

    private final SubjectService subjectService;

    @ApiOperation("添加科目")
    @PostMapping("/upload")
    public R addSubject(MultipartFile file) throws IOException {
        subjectService.save(file);
        return R.ok();
    }

    @ApiOperation("查询所有科目（树状结构）")
    @GetMapping
    public R findAll() {
        List<SubjectTree> subjects = subjectService.findAll();
        return R.ok(subjects);
    }

    @ApiOperation("根据id查询科目分类")
    @GetMapping("/{id}")
    public R findOne(@PathVariable Integer id) {
        Subject subject = subjectService.findById(id);
        return R.ok(subject);
    }

    @ApiOperation("查询一级科目分类")
    @GetMapping("/one")
    public R findParent() {
        List<Subject> subjects = subjectService.findParent();
        return R.ok(subjects);
    }
}
