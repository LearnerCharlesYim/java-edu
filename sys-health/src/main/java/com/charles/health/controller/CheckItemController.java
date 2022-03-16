package com.charles.health.controller;

import com.charles.common.domain.PageBean;
import com.charles.common.domain.R;
import com.charles.health.domain.dto.CheckItemQuery;
import com.charles.health.domain.entity.CheckItem;
import com.charles.health.service.CheckItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/item")
@RequiredArgsConstructor
public class CheckItemController {

    private final CheckItemService checkItemService;

    @PostMapping("/{pageNum}/{pageSize}")
    public R<PageBean<CheckItem>> page(@PathVariable Integer pageNum,
                                       @PathVariable Integer pageSize,
                                       @RequestBody(required = false) CheckItemQuery query) {
        return R.ok(checkItemService.page(pageNum, pageSize, query));
    }
}
