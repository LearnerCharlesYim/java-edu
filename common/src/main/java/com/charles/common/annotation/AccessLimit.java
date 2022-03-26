package com.charles.common.annotation;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@Documented
public @interface AccessLimit {

    int seconds() default 30;
    int maxCount() default 10;
}
