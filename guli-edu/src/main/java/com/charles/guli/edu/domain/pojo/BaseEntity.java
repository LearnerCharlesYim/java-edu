package com.charles.guli.edu.domain.pojo;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@MappedSuperclass
@Data
public class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer id;

    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    protected Date createdTime;

    @Temporal(TemporalType.TIMESTAMP)
    @UpdateTimestamp
    protected Date modifiedTime;

    protected Boolean deleted = false;

}
