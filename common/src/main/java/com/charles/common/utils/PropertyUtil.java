package com.charles.common.utils;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.util.StringUtils;

import java.beans.FeatureDescriptor;
import java.beans.PropertyDescriptor;
import java.util.Arrays;

public class PropertyUtil {
    /**
     * 只复制非Null字段
     *
     * @param source 源对象
     * @param dest   目标对象
     */
    public static void copyNotNullProperty(Object source, Object dest) {
        BeanUtils.copyProperties(source, dest, getNullPropertyNames(source));
    }

    /**
     * 只复制非Empty字段
     *
     * @param source 源对象
     * @param dest   目标对象
     */
    public static void copyNotEmptyProperty(Object source, Object dest) {
        BeanUtils.copyProperties(source, dest, getEmptyPropertyNames(source));
    }

    /**
     * get property name that value null
     *
     * @param source 源对象
     * @return 对象null属性名
     */
    private static String[] getNullPropertyNames(Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        PropertyDescriptor[] pds = src.getPropertyDescriptors();

        return Arrays.stream(pds)
                .map(FeatureDescriptor::getName)
                .filter(name -> src.getPropertyValue(name) == null)
                .toArray(String[]::new);
    }

    /**
     * get property name that value empty
     *
     * @param source 源对象
     * @return 对象empty属性名
     */
    private static String[] getEmptyPropertyNames(Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        PropertyDescriptor[] pds = src.getPropertyDescriptors();

        return Arrays.stream(pds)
                .map(FeatureDescriptor::getName)
                .filter(name -> {
                    Object value = src.getPropertyValue(name);
                    if (value == null) return true;
                    return value instanceof String && StringUtils.isEmpty(value);
                })
                .toArray(String[]::new);
    }
}
