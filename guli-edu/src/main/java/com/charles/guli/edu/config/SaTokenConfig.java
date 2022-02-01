package com.charles.guli.edu.config;

import cn.dev33.satoken.stp.StpInterface;
import com.charles.guli.edu.service.MenuService;
import com.charles.guli.edu.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class SaTokenConfig implements StpInterface {

    private final MenuService menuService;
    private final RoleService roleService;

    @Override
    public List<String> getPermissionList(Object o, String s) {
        return menuService.findCurrentMenus();
    }

    @Override
    public List<String> getRoleList(Object o, String s) {
        return roleService.findCurrentRoles();
    }
}
