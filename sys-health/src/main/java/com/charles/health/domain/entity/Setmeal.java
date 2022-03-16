package com.charles.health.domain.entity;

import com.charles.health.domain.enumerate.Gender;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * 体检套餐
 */
@Data
@Entity
@Table(name="t_setmeal")
public class Setmeal implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String code;

    private String helpCode;

    @Enumerated(EnumType.ORDINAL)
    private Gender sex;//套餐适用性别：0不限 1男 2女

    private String age;//套餐适用年龄

    private Float price;//套餐价格

    private String remark;

    private String attention;

    private String img;//套餐对应图片存储路径

    @ManyToMany
    @JoinTable(name = "t_setmeal_checkgroup",
            joinColumns = {@JoinColumn(name = "setmeal_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "checkgroup_id", referencedColumnName = "id")})
    private List<CheckGroup> checkGroups;//体检套餐对应的检查组，多对多关系
}
