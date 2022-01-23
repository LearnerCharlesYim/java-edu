package com.charles.guli.edu.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleDto {

    private Integer id;

    private String roleName;

    private String roleCode;

    private Integer sort;

    private Boolean status;

    private Date createdTime;
}
