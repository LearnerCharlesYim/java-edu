package com.charles.guli.edu.domain.pojo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@SQLDelete(sql = "update course set deleted = 1 where id = ?")
@Where(clause = "deleted = 0")
public class Course extends BaseEntity {

    private Integer teacherId;

    private Integer subjectId;

    private Integer subjectParentId;

    private String title;

    private BigDecimal price;

    private Integer lessonNum;

    private String cover;

    private Integer buyCount;

    private Integer viewCount;

    private Boolean status;

}
