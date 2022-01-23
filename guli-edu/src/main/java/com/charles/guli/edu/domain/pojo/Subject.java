package com.charles.guli.edu.domain.pojo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@SQLDelete(sql = "update subject set deleted = 1 where id = ?")
@Where(clause = "deleted = 0")
public class Subject extends BaseEntity{

    private String title;

    private Integer parentId;

    private Integer sort = 0;

}
