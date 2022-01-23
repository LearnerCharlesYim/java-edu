package com.charles.common.utils;

import com.charles.common.domain.CustomTree;
import org.springframework.beans.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class TreeUtil {

    public static List<? extends CustomTree> treeList(List<? extends CustomTree> list) {
        return list.stream()
                .filter(item -> item.getParentId() == 0)
                .peek(item -> item.setChildren(getChildren(item, list)))
                .collect(Collectors.toList());
    }

    private static List<CustomTree> getChildren(CustomTree parent, List<? extends CustomTree> list) {
        return list.stream()
                .filter(item -> Objects.equals(item.getParentId(), parent.getId()))
                .peek(item -> item.setChildren(getChildren(item, list)))
                .collect(Collectors.toList());
    }

    private static List<CustomTree> getChildren(CustomTree parent, List<? extends CustomTree> list, Class<? extends CustomTree> clazz) {
        return list.stream()
                .filter(item -> Objects.equals(item.getParentId(), parent.getId()))
                .map(item -> {
                    CustomTree result;
                    try {
                        CustomTree tree = clazz.getConstructor().newInstance();
                        BeanUtils.copyProperties(item, tree);
                        tree.setChildren(getChildren(tree, list, clazz));
                        result = tree;
                    } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                        e.printStackTrace();
                        result = null;
                    }
                    return result;
                })
                .collect(Collectors.toList());
    }

    public static List<? extends CustomTree> treeList(List<? extends CustomTree> list, Class<? extends CustomTree> clazz) {
        return list.stream()
                .filter(item -> item.getParentId() == 0)
                .map(item -> {
                    try {
                        CustomTree tree = clazz.getConstructor().newInstance();
                        BeanUtils.copyProperties(item, tree);
                        tree.setChildren(getChildren(tree, list, clazz));
                        return tree;
                    } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                        e.printStackTrace();
                        return null;
                    }
                })
                .collect(Collectors.toList());
    }
}
