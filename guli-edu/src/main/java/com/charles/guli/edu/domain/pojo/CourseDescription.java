package com.charles.guli.edu.domain.pojo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.Lob;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@SQLDelete(sql = "update course_description set deleted = 1 where id = ?")
@Where(clause = "deleted = 0")
public class CourseDescription extends BaseEntity {

    private Integer courseId;

    @Lob
    private String description;
}
