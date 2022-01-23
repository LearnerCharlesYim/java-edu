package com.charles.guli.edu.domain.pojo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@SQLDelete(sql = "update chapter set deleted = 1 where id = ?")
@Where(clause = "deleted = 0")
public class Chapter extends BaseEntity{

    private Integer courseId;

    private String title;

    private Integer sort = 0;

}
