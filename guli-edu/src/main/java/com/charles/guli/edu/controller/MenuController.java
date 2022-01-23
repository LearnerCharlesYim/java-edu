package com.charles.guli.edu.controller;

import com.charles.common.domain.R;
import com.charles.guli.edu.domain.dto.MenuTree;
import com.charles.guli.edu.domain.vo.MenuVo;
import com.charles.guli.edu.service.MenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "菜单管理")
@RequiredArgsConstructor
@RestController
@RequestMapping("/menu")
public class MenuController {

    private final MenuService menuService;

    @ApiOperation("添加菜单")
    @PostMapping
    public R addMenu(@RequestBody MenuVo menuParam) {
        menuService.addMenu(menuParam);
        return R.ok();
    }

    @ApiOperation("查询菜单")
    @GetMapping
    public R treeMenu() {
        List<MenuTree> menus = menuService.treeMenu();
        return R.ok(menus);
    }

    @ApiOperation("修改菜单")
    @PutMapping("/{id}")
    public R update(@PathVariable Integer id, @RequestBody MenuVo menuVo) {
        menuService.update(id, menuVo);
        return R.ok();
    }

    @ApiOperation("删除菜单")
    @DeleteMapping("/{id}")
    public R remove(@PathVariable Integer id) {
        menuService.remove(id);
        return R.ok();
    }
}
