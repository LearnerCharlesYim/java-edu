package com.charles.guli.edu.domain.pojo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@SQLDelete(sql = "update teacher set deleted = 1 where id = ?")
@Where(clause = "deleted = 0")
public class Teacher extends BaseEntity {

    @Column(length = 20, unique = true)
    private String name;

    @Lob
    private String intro;

    @Lob
    private String career;

    private Integer level;

    private String avatar;

    private Integer sort = 0;

}
