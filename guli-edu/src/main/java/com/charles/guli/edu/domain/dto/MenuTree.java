package com.charles.guli.edu.domain.dto;

import com.charles.common.utils.TreeUtil;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class MenuTree implements TreeUtil.TreeNode<MenuTree> {

    private Integer id;

    private String name;

    private String code;

    private String path;

    private String component;

    private String icon;

    private Integer sort;

    private Boolean status;

    private Integer parentId;

    private Date createdTime;

    private List<MenuTree> children;

}
