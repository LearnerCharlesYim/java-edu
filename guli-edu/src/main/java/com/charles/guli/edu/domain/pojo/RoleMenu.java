package com.charles.guli.edu.domain.pojo;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class RoleMenu extends BaseEntity {

    private Integer roleId;

    private Integer menuId;
}
