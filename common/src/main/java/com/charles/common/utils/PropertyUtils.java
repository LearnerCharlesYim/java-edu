package com.charles.common.utils;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.beans.PropertyDescriptor;
import java.util.HashSet;
import java.util.Set;

public class PropertyUtils {
    /**
     * 只复制非Null字段
     *
     * @param source 源对象
     * @param dest 目标对象
     */
    public static void copyNotNullProperty(Object source, Object dest) {
        BeanUtils.copyProperties(source, dest, getNullPropertyNames(source));
    }

    /**
     * get property name that value id null
     *
     * @param source 源对象
     * @return 对象null属性名
     */
    private static String[] getNullPropertyNames(Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        PropertyDescriptor[] pds = src.getPropertyDescriptors();
        Set<String> emptyNames = new HashSet<>();
        for (PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null) {
                emptyNames.add(pd.getName());
            }
        }
        return emptyNames.toArray(new String[0]);
    }
}
