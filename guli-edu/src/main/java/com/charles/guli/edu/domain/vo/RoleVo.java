package com.charles.guli.edu.domain.vo;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
public class RoleVo {

    @NotBlank(message = "角色名不为空")
    private String roleName;

    @NotBlank(message = "角色标识符不为空")
    private String roleCode;

    private Integer sort;

    private String remark;

    private Boolean status;

    private List<Integer> menuIds;
}
