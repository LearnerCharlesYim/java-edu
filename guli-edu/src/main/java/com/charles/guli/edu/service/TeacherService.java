package com.charles.guli.edu.service;

import com.charles.common.domain.PageBean;
import com.charles.common.domain.ResultCode;
import com.charles.common.execption.BizException;
import com.charles.common.utils.PropertyUtil;
import com.charles.guli.edu.domain.pojo.Teacher;
import com.charles.guli.edu.domain.vo.TeacherQuery;
import com.charles.guli.edu.repository.TeacherRepository;
import com.charles.guli.edu.utils.OssUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TeacherService {

    private final TeacherRepository teacherRepository;

    public List<Teacher> findAll() {
        return teacherRepository.findAll();
    }

    public PageBean<Teacher> findPage(Integer pageNum, Integer pageSize) {
        Page<Teacher> page = teacherRepository.findAll(PageRequest.of(pageNum - 1, pageSize, Sort.by(Sort.Direction.DESC, "createdTime")));
        return parsePage(pageNum, page);
    }

    public PageBean<Teacher> findByCondition(Integer pageNum, Integer pageSize, TeacherQuery teacherQuery) {
        Page<Teacher> page = teacherRepository.findCustom(teacherQuery, PageRequest.of(pageNum - 1, pageSize, Sort.by(Sort.Direction.DESC, "createdTime")));
        return PageBean.restPage(page);
    }

    private <T> PageBean<T> parsePage(Integer pageNum, Page<T> page) {
        PageBean<T> pageBean = new PageBean<>();
        pageBean.setCurrentPage(pageNum);
        pageBean.setContent(page.getContent());
        pageBean.setTotal((int) page.getTotalElements());
        pageBean.setPages(page.getTotalPages());
        return pageBean;
    }

    public Teacher add(Teacher teacher) {
        return teacherRepository.save(teacher);
    }

    public Teacher findById(Integer id) {
        Optional<Teacher> optional = teacherRepository.findById(id);
        if (optional.isEmpty()) throw new BizException(ResultCode.TEACHER_NOT_FOUND);
        return optional.get();
    }

    public Teacher update(Teacher teacher) {
        Teacher target = teacherRepository.findById(teacher.getId()).get();
        PropertyUtil.copyNotNullProperty(teacher, target);
        return teacherRepository.save(target);
    }

    public void remove(Integer id) {
        teacherRepository.deleteById(id);
    }

    public String uploadAvatar(MultipartFile avatar) throws IOException {
        return OssUtil.uploadToOss(avatar);
    }
}
