package com.charles.sys.share.domain.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@ApiModel("用户模型")
@MappedSuperclass
@Data
public class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty("主键id")
    protected Integer id;

    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    @ApiModelProperty("创建时间")
    protected Date createdTime;

    @Temporal(TemporalType.TIMESTAMP)
    @UpdateTimestamp
    @ApiModelProperty("修改时间")
    protected Date modifiedTime;

    @ApiModelProperty("是否删除")
    protected Boolean deleted = false;

}
