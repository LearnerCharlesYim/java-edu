package com.charles.sys.share.domain.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@ApiModel(value = "部门模型", parent = BaseEntity.class)
@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class Department extends BaseEntity {

    @ApiModelProperty("部门名称")
    private String name;

    @ApiModelProperty("部门主管")
    private String leader;

    @ApiModelProperty("部门职责")
    private String duty;

    @ApiModelProperty("部门状态")
    private Boolean status;

    @ApiModelProperty("部门员工")
    @OneToMany(mappedBy = "dept")
    private List<SysUser> users;

}
