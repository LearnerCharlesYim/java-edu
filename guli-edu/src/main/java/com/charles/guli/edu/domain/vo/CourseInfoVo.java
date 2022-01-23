package com.charles.guli.edu.domain.vo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CourseInfoVo {

    private Integer teacherId;

    private Integer subjectId;

    private String title;

    private BigDecimal price;

    private Integer lessonNum;

    private String cover;

    private String description;
}
