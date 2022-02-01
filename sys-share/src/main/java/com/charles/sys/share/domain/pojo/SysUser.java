package com.charles.sys.share.domain.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.util.Date;

@ApiModel(value = "用户模型", parent = BaseEntity.class)
@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class SysUser extends BaseEntity {

    @ApiModelProperty("用户名")
    private String username;

    @ApiModelProperty("密码")
    private String password;

    @ApiModelProperty("头像")
    private String avatar;

    @ApiModelProperty("地址")
    private String address;

    @ApiModelProperty("用户状态")
    private Boolean status = true;

    @ApiModelProperty("用户上次登录时间")
    private Date lastLogin;

    @ApiModelProperty("用户上次登录IP地址")
    private String ip;

    @ManyToOne
    @ApiModelProperty("用户所属部门")
    private Department dept;

}
