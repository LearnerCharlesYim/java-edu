package com.charles.common.utils;

import org.springframework.beans.BeanUtils;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class TreeUtil {
    public static <T extends TreeNode<T>> List<T> generateTree(List<T> list) {
        return list.stream()
                .filter(item -> item.getParentId() == 0)
                .peek(item -> item.setChildren(getChildren(item, list)))
                .collect(Collectors.toList());
    }

    public static <T extends TreeNode<T>, K extends TreeNode<K>> List<K> generateTree(List<T> list, Class<K> clazz) {
        return list.stream()
                .filter(item -> item.getParentId() == 0)
                .map(func(list, clazz))
                .collect(Collectors.toList());
    }

    private static <T extends TreeNode<T>> List<T> getChildren(TreeNode<T> parent, List<T> list) {
        return list.stream()
                .filter(item -> Objects.equals(item.getParentId(), parent.getId()))
                .peek(item -> item.setChildren(getChildren(item, list)))
                .collect(Collectors.toList());
    }

    private static <T extends TreeNode<T>, K extends TreeNode<K>> List<K> getChildren(TreeNode<K> parent, List<T> list, Class<K> clazz) {
        return list.stream()
                .filter(item -> Objects.equals(item.getParentId(), parent.getId()))
                .map(func(list, clazz))
                .collect(Collectors.toList());
    }

    private static <T extends TreeNode<T>, K extends TreeNode<K>> Function<T, K> func(List<T> list, Class<K> clazz) {
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

    public static <T extends TreeNode<T>> List<T> generateTree2(List<T> list) {
        List<T> result = new ArrayList<>();
        Map<Object, T> idNode = new HashMap<>(Math.max((int) (list.size() / .75f) + 1, 16));
        list.forEach(e -> idNode.put(e.getId(), e));
        idNode.forEach((id, node) -> {
            T parentNode = idNode.get(node.getParentId());
            if (parentNode != null) {
                List<T> children = parentNode.getChildren();
                if (children == null) {
                    children = new ArrayList<>();
                    parentNode.setChildren(children);
                }
                children.add(node);
            } else {
                result.add(node);
            }
        });
        return result;
    }

    public interface TreeNode<E> {
        Integer getId();
        Integer getParentId();
        void setChildren(List<E> children);
        List<E> getChildren();
    }
}
