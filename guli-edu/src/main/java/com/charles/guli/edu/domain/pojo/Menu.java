package com.charles.guli.edu.domain.pojo;

import com.charles.common.utils.TreeUtil;
import com.charles.guli.edu.domain.dto.MenuTree;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Transient;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@SQLDelete(sql = "update menu set deleted = 1 where id = ?")
@Where(clause = "deleted = 0")
public class Menu extends BaseEntity implements TreeUtil.TreeNode<Menu> {

    private String name;

    private String code;

    private String path;

    private String method;

    private String component;

    private String icon;

    private Integer sort;

    private Boolean status;

    private Integer parentId;

    @Enumerated(EnumType.ORDINAL)
    private Type type;

    @Transient
    private List<Menu> children;

    public enum Type {
        DICT,
        MENU,
        BUTTON
    }
}
