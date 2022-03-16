package com.charles.health.domain.entity;

import com.charles.health.domain.enumerate.Gender;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * 检查组
 */
@Data
@Entity
@Table(name = "t_checkgroup")
public class CheckGroup implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;//主键

    private String code;//编码

    private String name;//名称

    private String helpCode;//助记

    @Enumerated
    private Gender sex;//适用性别

    private String remark;//介绍

    private String attention;//注意事项

    @ManyToMany
    @JoinTable(name = "t_checkgroup_checkitem",
            joinColumns = {@JoinColumn(name = "checkgroup_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "checkitem_id", referencedColumnName = "id")})
    private List<CheckItem> checkItems;//一个检查组合包含多个检查项
}
