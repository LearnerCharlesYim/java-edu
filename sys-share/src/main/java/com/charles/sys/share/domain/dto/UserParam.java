package com.charles.sys.share.domain.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class UserParam {

    @NotBlank(message = "用户名不为空")
    @ApiModelProperty("用户名")
    private String username;

    @NotBlank(message = "密码不为空")
    @ApiModelProperty("密码")
    private String password;

    @ApiModelProperty("头像")
    private String avatar;

    @ApiModelProperty("地址")
    private String address;

    @ApiModelProperty("用户所属部门id")
    private Integer deptId;

}
