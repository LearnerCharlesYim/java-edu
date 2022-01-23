package com.charles.guli.edu.domain.pojo;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class UserRole extends BaseEntity {

    private Integer userId;

    private Integer roleId;
}
