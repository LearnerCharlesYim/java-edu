package com.charles.common.utils;

import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

public class TreeUtil {

    public static <T extends TreeNode> List<T> generateTree(List<T> list) {
        return list.stream()
                .filter(item -> item.getParentId() == 0)
                .peek(item -> item.setChildren(getChildren(item, list)))
                .collect(Collectors.toList());
    }

    public static <T extends TreeNode, K extends TreeNode> List<K> generateTree(List<T> list, Class<K> clazz) {
        return list.stream()
                .filter(item -> item.getParentId() == 0)
                .map(function(list, clazz))
                .collect(Collectors.toList());
    }

    private static <T extends TreeNode> List<T> getChildren(TreeNode parent, List<T> list) {
        return list.stream()
                .filter(item -> Objects.equals(item.getParentId(), parent.getId()))
                .peek(item -> item.setChildren(getChildren(item, list)))
                .collect(Collectors.toList());
    }

    private static <T extends TreeNode, K extends TreeNode> List<K> getChildren(TreeNode parent, List<T> list, Class<K> clazz) {
        return list.stream()
                .filter(item -> Objects.equals(item.getParentId(), parent.getId()))
                .map(function(list, clazz))
                .collect(Collectors.toList());
    }

    private static <T extends TreeNode, K extends TreeNode> Function<T, K> function(List<T> list, Class<K> clazz) {
        return item -> {
            K result;
            try {
                K treeNode = clazz.getConstructor().newInstance();
                BeanUtils.copyProperties(item, treeNode);
                treeNode.setChildren(getChildren(treeNode, list, clazz));
                result = treeNode;
            } catch (Exception e) {
                e.printStackTrace();
                result = null;
            }
            return result;
        };
    }

    public interface TreeNode {

        Long getId();

        Long getParentId();

        <T extends TreeNode> void setChildren(List<T> children);
    }
}
