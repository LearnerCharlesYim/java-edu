package com.charles.guli.edu.domain.vo;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class LoginParam {

    @NotBlank(message = "用户名不为空")
    private String username;

    @NotBlank(message = "密码不为空")
    private String password;
}
