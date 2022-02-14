package com.charles.guli.edu.config;

import cn.dev33.satoken.session.SaSession;
import cn.dev33.satoken.session.SaSessionCustomUtil;
import cn.dev33.satoken.stp.StpInterface;
import cn.dev33.satoken.stp.StpUtil;
import com.charles.guli.edu.service.MenuService;
import com.charles.guli.edu.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class SaTokenConfig implements StpInterface {

    private final RoleService roleService;

    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
        Set<String> permissionList = new HashSet<>();
        for (String roleCode : getRoleList(loginId, loginType)) {
            SaSession roleSession = SaSessionCustomUtil.getSessionById("role-" + roleCode);
            List<String> list = roleSession.get("Permission_List", () -> {
                return roleService.findRoleMenus(roleCode);     // 从数据库查询这个角色所拥有的权限列表
            });
            permissionList.addAll(list);
        }
        return new ArrayList<>(permissionList);
    }

    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
        SaSession session = StpUtil.getSessionByLoginId(loginId);
        return session.get("Role_List", roleService::findCurrentRoles);
    }
}
