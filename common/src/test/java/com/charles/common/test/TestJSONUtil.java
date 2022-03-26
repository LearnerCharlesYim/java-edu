package com.charles.common.test;

import cn.hutool.json.JSONUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.Test;

public class TestJSONUtil {

    @Test
    public void test01() {
        Student s1 = new Student("Tom", 12, null);
        String result1 = JSONUtil.toJsonStr(s1);
        String result2 = JSONUtil.toJsonPrettyStr(s1);
//        System.out.println(result2);
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    static class Student {
        private String username;
        private Integer age;
        private String sex;
    }
}
