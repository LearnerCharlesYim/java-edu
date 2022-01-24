package com.charles.guli.edu.controller;

import com.charles.common.domain.PageBean;
import com.charles.common.domain.R;
import com.charles.guli.edu.domain.dto.RoleDto;
import com.charles.guli.edu.domain.dto.RoleInfo;
import com.charles.guli.edu.domain.vo.RoleQuery;
import com.charles.guli.edu.domain.vo.RoleVo;
import com.charles.guli.edu.service.RoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Api(tags = "角色管理")
@RequiredArgsConstructor
@RestController
@RequestMapping("/role")
public class RoleController {

    private final RoleService roleService;

    @ApiOperation("角色列表")
    @PostMapping("/{currentPage}/{pageSize}")
    public R<PageBean<RoleDto>> roleList(@PathVariable Integer currentPage,
                      @PathVariable Integer pageSize,
                      @RequestBody(required = false) RoleQuery roleQuery) {
        if (roleQuery == null) roleQuery = new RoleQuery();
        PageBean<RoleDto> roles = roleService.findPage(currentPage, pageSize, roleQuery);
        return R.ok(roles);
    }

    @ApiOperation("根据角色ID查询详情")
    @GetMapping("{id}")
    public R<RoleInfo> findById(@PathVariable Integer id) {
        RoleInfo role = roleService.findById(id);
        return R.ok(role);
    }

    @ApiOperation("角色添加")
    @PostMapping
    public R<Void> addRole(@RequestBody @Validated RoleVo roleVo) {
        roleService.addRole(roleVo);
        return R.ok();
    }

    @ApiOperation("角色修改")
    @PutMapping("{id}")
    public R<Void> updateRole(@PathVariable Integer id, @Validated @RequestBody RoleVo roleVo) {
        roleService.updateRole(id, roleVo);
        return R.ok();
    }
}
