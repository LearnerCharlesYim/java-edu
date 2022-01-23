package com.charles.guli.edu.domain.dto;

import lombok.Data;

import java.util.List;

@Data
public class MenuTree {

    private Integer id;

    private String name;

    private String code;

    private String path;

    private String component;

    private String icon;

    private Integer sort;

    private Boolean status;

    private Integer parentId;

    private List<MenuTree> children;

}
