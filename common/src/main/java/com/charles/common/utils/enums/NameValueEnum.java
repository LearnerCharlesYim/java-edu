package com.charles.common.utils.enums;

public interface NameValueEnum<T> extends ValueEnum<T> {
    /**
     * 获取枚举名称
     *
     * @return 枚举名
     */
    String getName();
}
