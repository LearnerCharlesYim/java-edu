package com.charles.health.domain.dto;

import com.charles.common.annotation.Query;
import lombok.Data;

@Data
public class CheckItemQuery  {

    @Query
    private Integer code;

    @Query(type = Query.Type.INNER_LIKE)
    private String name;

    @Query
    private Integer sex;

}
