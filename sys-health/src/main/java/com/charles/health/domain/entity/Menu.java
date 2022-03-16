package com.charles.health.domain.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

/**
 * 菜单
 */
@Data
@Entity
@Table(name = "t_menu")
public class Menu implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name; // 菜单名称

    private String linkUrl; // 访问路径

    private String path;//菜单项所对应的路由路径

    private Integer priority; // 优先级（用于排序）

    private String description; // 描述

    private String icon;//图标

    private Integer level; //级别

    @ManyToMany
    @JoinTable(name = "t_role_menu",
            joinColumns = {@JoinColumn(name = "menu_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")})
    private Set<Role> roles;//角色集合

    @OneToMany(mappedBy = "parentMenu")
    private List<Menu> children;//子菜单集合

    @ManyToOne
    private Menu parentMenu;//父菜单id
}
