package com.charles.sys.share.domain.pojo;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;

@ApiModel(value = "用户模型", parent = BaseEntity.class)
@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class Role extends BaseEntity{

    private String name;

    private String code;

    private String description;

}
