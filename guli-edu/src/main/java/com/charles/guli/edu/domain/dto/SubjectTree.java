package com.charles.guli.edu.domain.dto;

import lombok.Data;

import java.util.List;

@Data
public class SubjectTree {

    private Integer id;

    private String title;

    private Integer sort;

    private Integer parentId;

    private List<SubjectTree> children;
}
