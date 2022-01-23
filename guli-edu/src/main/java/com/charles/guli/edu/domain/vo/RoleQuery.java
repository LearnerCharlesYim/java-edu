package com.charles.guli.edu.domain.vo;

import lombok.Data;

import java.util.Date;

@Data
public class RoleQuery {

    private String roleName;

    private String roleCode;

    private Boolean status;

    private Date begin;

    private Date end;

}
