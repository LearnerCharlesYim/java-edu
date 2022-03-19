package com.charles.guli.edu.test;

import com.charles.guli.edu.domain.dto.MenuTree;
import com.charles.guli.edu.service.MenuService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class testTree {

    private final ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private MenuService menuService;

    @Test
    public void test01() throws Exception {
        List<MenuTree> menuTrees = menuService.tree();
        System.out.println(mapper.writeValueAsString(menuTrees));
    }

    @Test
    public void test02() throws Exception {
        System.out.println(mapper.writeValueAsString(menuService.tree2()));
    }
}
