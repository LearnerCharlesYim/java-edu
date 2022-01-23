package com.charles.guli.edu.test;

import com.charles.common.domain.CustomTree;
import com.charles.common.utils.TreeUtil;
import com.charles.guli.edu.repository.MenuRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.stream.Collectors;

@SpringBootTest
@RequiredArgsConstructor
public class TestTreeUtil {

    @Data
    static
    class Menu implements CustomTree {

        private Integer id;
        private String name;
        private Integer parentId;
        private List<Menu> children;

        @Override
        public Integer getParentId() {
            return this.parentId;
        }

        @Override
        public void setChildren(List<? extends CustomTree> children) {
            this.children = (List<Menu>) children;
        }
    }

    @Autowired
    private MenuRepository menuRepository;

    @Test
    public void testTreeUtil() throws JsonProcessingException {
        List<com.charles.guli.edu.domain.pojo.Menu> menus = menuRepository.findAll();
        List<Menu> menuList = menus.stream().map(menu -> {
            Menu m = new Menu();
            BeanUtils.copyProperties(menu, m);
            return m;
        }).collect(Collectors.toList());

        List<Menu> result = (List<Menu>) TreeUtil.treeList(menuList);
        System.out.println(new ObjectMapper().writeValueAsString(result));
    }
}
