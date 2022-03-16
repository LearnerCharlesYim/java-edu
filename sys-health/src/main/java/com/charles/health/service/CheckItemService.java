package com.charles.health.service;

import com.charles.common.domain.PageBean;
import com.charles.common.utils.QueryHelp;
import com.charles.health.domain.dto.CheckItemQuery;
import com.charles.health.domain.entity.CheckItem;
import com.charles.health.repository.CheckItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class CheckItemService {

    private final CheckItemRepository checkItemRepository;

    public PageBean<CheckItem> page(Integer pageNum, Integer pageSize, CheckItemQuery query) {
        if(query == null) query = new CheckItemQuery();
        CheckItemQuery finalQuery = query;
        Page<CheckItem> page = checkItemRepository.findAll(((root, criteriaQuery, cb) -> QueryHelp.getPredicate(root, finalQuery, cb))
                , PageRequest.of(pageNum - 1, pageSize));
        return PageBean.restPage(page);
    }
}
