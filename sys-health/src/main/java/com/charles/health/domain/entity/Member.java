package com.charles.health.domain.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 会员
 */
@Data
@Entity
@Table(name = "t_member")
public class Member implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;//主键

    private String fileNumber;//档案号

    private String name;//姓名

    @Enumerated
    private User.Gender sex;//性别

    private String idCard;//身份证号

    private String phone;//手机号

    @Temporal(TemporalType.DATE)
    @CreationTimestamp
    private Date regTime;//注册时间

    private String password;//登录密码

    private String email;//邮箱

    private Date birthday;//出生日期

    private String remark;//备注
}
