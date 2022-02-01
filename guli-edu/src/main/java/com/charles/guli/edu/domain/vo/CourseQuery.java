package com.charles.guli.edu.domain.vo;

import com.charles.common.annotation.Query;
import lombok.Data;

@Data
public class CourseQuery {

    @Query
    private String title;

}
