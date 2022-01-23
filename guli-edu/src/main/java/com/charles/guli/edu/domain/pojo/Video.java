package com.charles.guli.edu.domain.pojo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@SQLDelete(sql = "update video set deleted = 1 where id = ?")
@Where(clause = "deleted = 0")
public class Video extends BaseEntity {

    enum Status {
        EMPTY,
        TRANSCODING,
        NORMAL
    }

    private Integer courseId;

    private Integer chapterId;

    private String title;

    private String videoSourceId;

    private String videoOriginalName;

    private Integer sort = 0;

    private Integer playCount;

    private Boolean free;

    private Double duration;

    @Enumerated(EnumType.ORDINAL)
    private Status status;

    private Long size;

}
