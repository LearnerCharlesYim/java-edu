package com.charles.guli.edu.service;

import cn.dev33.satoken.stp.StpUtil;
import com.charles.common.utils.PropertyUtil;
import com.charles.common.utils.TreeUtil;
import com.charles.guli.edu.domain.dto.MenuTree;
import com.charles.guli.edu.domain.pojo.Menu;
import com.charles.guli.edu.domain.vo.MenuVo;
import com.charles.guli.edu.repository.MenuRepository;
import com.charles.guli.edu.repository.RoleMenuRepository;
import com.charles.guli.edu.repository.UserRoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class MenuService {

    private final MenuRepository menuRepository;
    private final UserRoleRepository userRoleRepository;
    private final RoleMenuRepository roleMenuRepository;

    public void addMenu(MenuVo menuParam) {
        Menu menu = new Menu();
        BeanUtils.copyProperties(menuParam, menu);
        if (menuParam.getType() == 0) {
            menu.setType(Menu.Type.DICT);
        } else if (menuParam.getType() == 1) {
            menu.setType(Menu.Type.MENU);
        } else if (menuParam.getType() == 2) {
            menu.setType(Menu.Type.BUTTON);
        }
        menuRepository.save(menu);
    }

    public List<MenuTree> treeMenu() {
        List<Menu> menus = menuRepository.findByStatus(true, Sort.by(Sort.Direction.ASC, "sort"));
        return getMenuFirst(menus);
    }

    private List<MenuTree> getMenuFirst(List<Menu> menus) {
        return menus.stream()
                .filter(menu -> menu.getParentId() == 0)
                .map(menu -> {
                    MenuTree menuTree = new MenuTree();
                    BeanUtils.copyProperties(menu, menuTree);
                    menuTree.setChildren(getMenuChildren(menuTree, menus));
                    return menuTree;
                })
                .collect(Collectors.toList());
    }

    private List<MenuTree> getMenuChildren(MenuTree parent, List<Menu> menus) {
        return menus.stream()
                .filter(menu -> Objects.equals(menu.getParentId(), parent.getId()))
                .map(menu -> {
                    MenuTree menuTree = new MenuTree();
                    BeanUtils.copyProperties(menu, menuTree);
                    menuTree.setChildren(getMenuChildren(menuTree, menus));
                    return menuTree;
                })
                .collect(Collectors.toList());
    }

    public void update(Integer id, MenuVo menuVo) {
        Menu menu = menuRepository.findById(id).get();
        PropertyUtil.copyNotNullProperty(menuVo, menu);
        menuRepository.save(menu);
    }

    public void remove(Integer id) {
        menuRepository.deleteById(id);
        List<Menu> children = menuRepository.findByParentId(id);
        children.forEach(menu -> remove(menu.getId()));
    }

    public List<String> findCurrentMenus() {
        List<Integer> roleIds = userRoleRepository.findRoleIdsByUserId(StpUtil.getLoginIdAsInt());
        Set<Integer> menuIds = roleMenuRepository.findMenuIdsByRoleIds(roleIds);
        List<String> paths = menuRepository.findPath(menuIds);
        return paths.stream().filter(Objects::nonNull).collect(Collectors.toList());
    }

    public List<String> findAllPaths() {
        return menuRepository.findAllPaths();
    }

    public List<MenuTree> tree() {
        List<Menu> menus = menuRepository.findByStatus(true, Sort.by(Sort.Direction.ASC, "sort"));
        List<MenuTree> treeList = menus.stream().map(menu -> {
            MenuTree tree = new MenuTree();
            BeanUtils.copyProperties(menu, tree);
            return tree;
        }).collect(Collectors.toList());
        return TreeUtil.generateTree(treeList);
    }

    public List<MenuTree> tree2() {
        List<Menu> menus = menuRepository.findByStatus(true, Sort.by(Sort.Direction.ASC, "sort"));
        return TreeUtil.generateTree(menus, MenuTree.class);
    }

    public List<Menu> tree3() {
        List<Menu> menus = menuRepository.findByStatus(true, Sort.by(Sort.Direction.ASC, "sort"));
        return TreeUtil.generateTree2(menus);
    }
}
