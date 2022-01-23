package com.charles.guli.edu.domain.vo;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class MenuVo {

    @NotBlank
    private String name;

    private String code;

    private String path;

    private String component;

    private String icon;

    private Integer sort;

    private Boolean status;

    private Integer parentId;

    private Integer type;
}
