package com.charles.common.execption;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ArrayUtil;
import com.charles.common.domain.ResultCode;
import org.springframework.util.StringUtils;

import java.util.Collection;
import java.util.Objects;
import java.util.function.Supplier;

@SuppressWarnings("all")
public final class BizAssert {
    public static Integer defaultErrorCode = ResultCode.COMMON_FAIL.getCode();

    public static void setDefaultErrorCode(Integer errorCode) {
        defaultErrorCode = errorCode;
    }

    public static void setDefaultErrorCode(BaseErrorInfoInterface errorInfo) {
        defaultErrorCode = errorInfo.getCode();
    }

    public static void notNull(Object object, String msg) {
        if (object == null) {
            throw new BizException(defaultErrorCode, msg);
        }
    }

    public static void notNull(Object object, Supplier<String> supplier) {
        if (object == null) {
            throw new BizException(defaultErrorCode, supplier.get());
        }
    }

    public static void notNull(Object object, BaseErrorInfoInterface errorInfo) {
        if (object == null) {
            throw new BizException(errorInfo);
        }
    }

    public static void isNull(Object object, String msg) {
        isTrue(object == null, msg);
    }

    public static void isNull(Object object, Supplier<String> supplier) {
        isTrue(object == null, supplier);
    }

    public static void isNull(Object object, BaseErrorInfoInterface errorInfo) {
        isTrue(object == null, errorInfo);
    }

    public static void notEmpty(String str, String msg) {
        isTrue(!StringUtils.isEmpty(str), msg);
    }

    public static void notEmpty(String str, Supplier<String> supplier) {
        isTrue(!StringUtils.isEmpty(str), supplier);
    }

    public static void notEmpty(String str, BaseErrorInfoInterface errorInfo) {
        isTrue(!StringUtils.isEmpty(str), errorInfo);
    }

    public static void notEmpty(Collection<?> collection, String msg) {
        isTrue(CollUtil.isNotEmpty(collection), msg);
    }

    public static void notEmpty(Collection<?> collection, Supplier<String> supplier) {
        isTrue(CollUtil.isNotEmpty(collection), supplier);
    }

    public static void notEmpty(Collection<?> collection, BaseErrorInfoInterface errorInfo) {
        isTrue(CollUtil.isNotEmpty(collection), errorInfo);
    }

    public static void empty(Collection<?> collection, String msg) {
        isTrue(CollUtil.isEmpty(collection), msg);
    }

    public static void empty(Collection<?> collection, Supplier<String> supplier) {
        isTrue(CollUtil.isEmpty(collection), supplier);
    }

    public static void empty(Collection<?> collection, BaseErrorInfoInterface errorInfo) {
        isTrue(CollUtil.isEmpty(collection), errorInfo);
    }

    public static void equals(Object o1, Object o2, String msg) {
        isTrue(Objects.equals(o1, o2), msg);
    }

    public static void equals(Object o1, Object o2, Supplier<String> supplier) {
        isTrue(Objects.equals(o1, o2), supplier);
    }

    public static void equals(Object o1, Object o2, BaseErrorInfoInterface errorInfo) {
        isTrue(Objects.equals(o1, o2), errorInfo);
    }

    public static void notEquals(Object o1, Object o2, String msg) {
        isTrue(!Objects.equals(o1, o2), msg);
    }

    public static void notEquals(Object o1, Object o2, Supplier<String> supplier) {
        isTrue(!Objects.equals(o1, o2), supplier);
    }

    public static void notEquals(Object o1, Object o2, BaseErrorInfoInterface errorInfo) {
        isTrue(!Objects.equals(o1, o2), errorInfo);
    }

    public static <T> void contains(T o1, T[] objs, String msg) {
        isTrue(ArrayUtil.contains(objs, o1), msg);
    }

    public static <T> void contains(T o1, T[] objs, Supplier<String> supplier) {
        isTrue(ArrayUtil.contains(objs, o1), supplier);
    }

    public static <T> void contains(T o1, T[] objs, BaseErrorInfoInterface errorInfo) {
        isTrue(ArrayUtil.contains(objs, o1), errorInfo);
    }

    public static <T> void contains(T o1, Collection<T> objs, String msg) {
        isTrue(CollUtil.contains(objs, o1), msg);
    }

    public static <T> void contains(T o1, Collection<T> objs, Supplier<String> supplier) {
        isTrue(CollUtil.contains(objs, o1), supplier);
    }

    public static <T> void contains(T o1, Collection<T> objs, BaseErrorInfoInterface errorInfo) {
        isTrue(CollUtil.contains(objs, o1), errorInfo);
    }

    public static void isTrue(boolean expression, String msg) {
        if (!expression) {
            throw new BizException(defaultErrorCode, msg);
        }
    }

    public static void isTrue(boolean expression, Supplier<String> supplier) {
        if (!expression) {
            throw new BizException(defaultErrorCode, supplier.get());
        }
    }

    public static void isTrue(boolean expression, BaseErrorInfoInterface errorInfo) {
        if (!expression) {
            throw new BizException(errorInfo);
        }
    }

    public static void fail(String message) {
        throw new BizException(message);
    }

    public static void fail(BaseErrorInfoInterface errorInfo) {
        throw new BizException(errorInfo);
    }
}
