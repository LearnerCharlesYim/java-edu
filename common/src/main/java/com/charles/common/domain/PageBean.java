package com.charles.common.domain;

import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.List;

@Data
public class PageBean<T> {

    private Integer currentPage;

    private Integer total;

    private Integer pages;

    private List<T> content;

    public static <T> PageBean<T> restPage(Page<T> pageInfo) {
        PageBean<T> result = new PageBean<>();
        result.setPages(pageInfo.getTotalPages());
        result.setCurrentPage(pageInfo.getNumber() + 1);
        result.setTotal((int) pageInfo.getTotalElements());
        result.setContent(pageInfo.getContent());
        return result;
    }
}
