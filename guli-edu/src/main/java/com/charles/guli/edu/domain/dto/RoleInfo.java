package com.charles.guli.edu.domain.dto;

import lombok.Data;

import java.util.List;

@Data
public class RoleInfo {

    private Integer id;

    private String roleName;

    private String roleCode;

    private Integer sort;

    private Boolean status;

    private String remark;

    private List<Menu> menus;

    private List<Integer> menuKeys;

    @Data
    public static class Menu {
        private Integer id;
        private String label;
        private List<Menu> children;
    }

}
