package com.charles.guli.edu.domain.vo;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
public class UserVo {

    @NotBlank
    private String username;

    @NotBlank
    private String password;

    private String avatar;

    private String intro;

    private List<Integer> rolesId;
}
