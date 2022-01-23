package com.charles.guli.edu.domain.pojo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Entity;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@SQLDelete(sql = "update user set deleted = 1 where id = ?")
@Where(clause = "deleted = 0")
public class User extends BaseEntity {

    @Column(unique = true)
    private String username;

    private String password;

    private String avatar;

    private String intro;

}
