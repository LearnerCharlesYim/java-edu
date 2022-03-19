package com.charles.guli.edu.domain.vo;

import com.charles.guli.edu.validator.CheckUsername;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
public class UserVo {

    @NotBlank(message = "用户名不为空")
    @CheckUsername(message = "用户名已存在")
    private String username;

    @NotBlank(message = "密码不为空")
    private String password;

    private String avatar;

    private String intro;

    private List<Integer> rolesId;
}
