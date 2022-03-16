package com.charles.health.domain.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * 用户
 */
@Data
@Entity
@Table(name="t_user")
public class User implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; // 主键

    private Date birthday; // 生日

    @Enumerated
    private Gender gender; // 性别

    private String username; // 用户名，唯一

    private String password; // 密码

    private String remark; // 备注

    private Boolean status = true; // 状态

    private String telephone; // 联系电话

    @ManyToMany
    @JoinTable(name = "t_user_role",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")})
    private Set<Role> roles;//对应角色集合

    public enum Gender{
        UNKNOWN,
        MAIL,
        FEMAIL;
    }
}
