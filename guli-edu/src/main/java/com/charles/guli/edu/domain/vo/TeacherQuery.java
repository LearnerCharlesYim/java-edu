package com.charles.guli.edu.domain.vo;

import com.charles.common.annotation.Query;
import lombok.Data;

import java.util.Date;

@Data
public class TeacherQuery {

    @Query(type = Query.Type.INNER_LIKE)
    private String name;

    @Query
    private Integer level;

    @Query(propName = "createdTime", type = Query.Type.GREATER_THAN)
    private Date begin;

    @Query(propName = "createdTime", type = Query.Type.LESS_THAN)
    private Date end;
}
