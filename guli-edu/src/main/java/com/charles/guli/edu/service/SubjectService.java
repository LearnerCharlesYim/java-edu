package com.charles.guli.edu.service;

import com.alibaba.excel.EasyExcel;
import com.charles.guli.edu.domain.dto.SubjectTree;
import com.charles.guli.edu.domain.dto.excel.SubjectData;
import com.charles.guli.edu.domain.dto.excel.SubjectExcelListener;
import com.charles.guli.edu.domain.pojo.Subject;
import com.charles.guli.edu.repository.SubjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SubjectService {

    private final SubjectRepository subjectRepository;

    public void save(MultipartFile file) throws IOException {
        EasyExcel.read(file.getInputStream(), SubjectData.class, new SubjectExcelListener(subjectRepository)).sheet().doRead();
    }

    public List<SubjectTree> findAll() {
        List<Subject> list = subjectRepository.findAll();
        return findSubjectOne(list);
    }

    private List<SubjectTree> findSubjectOne(List<Subject> list) {
        return list.stream()
                .filter(subject -> subject.getParentId() == 0)
                .map(subject -> {
                    SubjectTree subjectTree = new SubjectTree();
                    BeanUtils.copyProperties(subject, subjectTree);
                    subjectTree.setChildren(findChildSubject(subjectTree, list));
                    return subjectTree;
                })
                .collect(Collectors.toList());
    }

    private List<SubjectTree> findChildSubject(SubjectTree parent, List<Subject> list) {
        return list.stream()
                .filter(child -> Objects.equals(child.getParentId(), parent.getId()))
                .map(child -> {
                    SubjectTree subjectTree = new SubjectTree();
                    BeanUtils.copyProperties(child, subjectTree);
                    subjectTree.setChildren(findChildSubject(subjectTree, list));
                    return subjectTree;
                })
                .collect(Collectors.toList());
    }

    public Subject findById(Integer id) {
        return subjectRepository.findById(id).get();
    }

    public List<Subject> findParent() {
        return subjectRepository.findByParentId(0);
    }
}
