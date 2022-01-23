package com.charles.common.domain;

import lombok.Data;

import java.util.List;

@Data
public class PageBean<T> {

    private Integer currentPage;

    private Integer total;

    private Integer pages;

    private List<T> content;
}
