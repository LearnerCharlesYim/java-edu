package com.charles.health.domain.entity;

import com.charles.health.domain.enumerate.Gender;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 检查项
 */
@Data
@Entity
@Table(name = "t_checkitem")
public class CheckItem implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;//主键

    private String code;//项目编码

    private String name;//项目名称

    @Enumerated(EnumType.ORDINAL)
    private Gender sex;//适用性别

    private String age;//适用年龄（范围），例如：20-50

    private Float price;//价格

    @Enumerated(EnumType.ORDINAL)
    private Type type;//检查项类型，分为检查和检验两种类型

    private String remark;//项目说明

    private String attention;//注意事项

    public enum Type {
        CHECK("检查"),
        EXAM("检验");

        Type(String type) {
        }
    }
}
