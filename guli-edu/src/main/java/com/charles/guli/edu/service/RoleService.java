package com.charles.guli.edu.service;

import cn.dev33.satoken.stp.StpUtil;
import com.charles.common.domain.PageBean;
import com.charles.common.utils.PropertyUtils;
import com.charles.guli.edu.domain.dto.RoleDto;
import com.charles.guli.edu.domain.dto.RoleInfo;
import com.charles.guli.edu.domain.pojo.Menu;
import com.charles.guli.edu.domain.pojo.Role;
import com.charles.guli.edu.domain.pojo.RoleMenu;
import com.charles.guli.edu.domain.vo.RoleQuery;
import com.charles.guli.edu.domain.vo.RoleVo;
import com.charles.guli.edu.repository.MenuRepository;
import com.charles.guli.edu.repository.RoleMenuRepository;
import com.charles.guli.edu.repository.RoleRepository;
import com.charles.guli.edu.repository.UserRoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;
    private final RoleMenuRepository roleMenuRepository;
    private final MenuRepository menuRepository;
    private final UserRoleRepository userRoleRepository;


    public void addRole(RoleVo roleVo) {
        Role role = new Role();
        BeanUtils.copyProperties(roleVo, role);
        Role newRole = roleRepository.save(role);
        Integer roleId = newRole.getId();
        List<Integer> menuIds = roleVo.getMenuIds();
        if (menuIds != null) setRoleMenu(roleId, menuIds);
    }

    private void setRoleMenu(Integer roleId, List<Integer> menuIds) {
        roleMenuRepository.deleteAllByRoleId(roleId);
        menuIds.forEach(menuId -> {
            RoleMenu relation = new RoleMenu();
            relation.setRoleId(roleId);
            relation.setMenuId(menuId);
            roleMenuRepository.save(relation);
        });
    }

    public void updateRole(Integer id, RoleVo roleVo) {
        Role role = roleRepository.findById(id).get();
        PropertyUtils.copyNotNullProperty(roleVo, role);
        roleRepository.save(role);
        List<Integer> menuIds = roleVo.getMenuIds();
        if (menuIds != null) setRoleMenu(role.getId(), menuIds);
    }

    public PageBean<RoleDto> findPage(Integer currentPage, Integer pageSize, RoleQuery roleQuery) {
        Page<RoleDto> page =
                roleRepository.findCustom(roleQuery, PageRequest.of(currentPage - 1, pageSize, Sort.by(Sort.Direction.ASC, "sort")));
        PageBean<RoleDto> result = new PageBean<>();

        result.setCurrentPage(currentPage);
        result.setTotal((int) page.getTotalElements());
        result.setPages(page.getTotalPages());
        result.setContent(page.getContent());
        return result;
    }

    public RoleInfo findById(Integer id) {
        Role role = roleRepository.findById(id).get();
        List<Integer> menuIds = roleMenuRepository.findCustomMenuIds(id);
        List<Menu> menus = menuRepository.findAllById(menuIds);

        RoleInfo roleInfo = new RoleInfo();
        BeanUtils.copyProperties(role, roleInfo);
        roleInfo.setMenuKeys(menuIds);

        List<RoleInfo.Menu> menuTree = getParentMenu(menus);
        roleInfo.setMenus(menuTree);
        return roleInfo;
    }

    private List<RoleInfo.Menu> getParentMenu(List<Menu> menus) {
        return menus.stream()
                .filter(menu -> menu.getParentId() == 0)
                .map(menu -> {
                    RoleInfo.Menu menuTree = new RoleInfo.Menu();
                    menuTree.setId(menu.getId());
                    menuTree.setLabel(menu.getName());
                    menuTree.setChildren(getChildrenMenu(menuTree, menus));
                    return menuTree;
                })
                .collect(Collectors.toList());
    }

    private List<RoleInfo.Menu> getChildrenMenu(RoleInfo.Menu menuTree, List<Menu> menus) {
        return menus.stream()
                .filter(menu -> Objects.equals(menu.getParentId(), menuTree.getId()))
                .map(menu -> {
                    RoleInfo.Menu childTree = new RoleInfo.Menu();
                    childTree.setId(menu.getId());
                    childTree.setLabel(menu.getName());
                    childTree.setChildren(getChildrenMenu(childTree, menus));
                    return childTree;
                })
                .collect(Collectors.toList());
    }

    public List<String> findCurrentRoles() {
        List<Integer> roleIds = userRoleRepository.findRoleIdsByUserId(StpUtil.getLoginIdAsInt());
        return roleRepository.findRoleCodes(roleIds);
    }

    public List<String> findRoleMenus(String roleCode) {
        Role role = roleRepository.findByRoleCode(roleCode);
        List<Integer> MenuIds = roleMenuRepository.findCustomMenuIds(role.getId());
        List<Menu> menus = menuRepository.findAllById(MenuIds);
        return menus.stream().map(Menu::getPath).filter(Objects::nonNull).distinct().collect(Collectors.toList());
    }
}
