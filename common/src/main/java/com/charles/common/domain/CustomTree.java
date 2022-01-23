package com.charles.common.domain;

import java.util.List;

public interface CustomTree {

    Integer getId();

    Integer getParentId();

    void setChildren(List<? extends CustomTree> children);
}
