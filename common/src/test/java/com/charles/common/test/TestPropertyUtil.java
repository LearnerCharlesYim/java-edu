package com.charles.common.test;

import cn.hutool.http.HtmlUtil;
import com.charles.common.utils.PropertyUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.Test;
import org.springframework.beans.BeanUtils;

public class TestPropertyUtil {

    @Test
    public void test() {
        TestBean b1 = new TestBean(1, "Tom", "111111", "pig.png", "Tokyo.Japan");
        TestBean b2 = new TestBean(2, "", "222222", "", null);
        System.out.println(b1);
//        PropertyUtil.copyNotNullProperty(b2,b1);
//        PropertyUtil.copyNotEmptyProperty(b2,b1);
//        BeanUtils.copyProperties(b2,b1);
//        System.out.println(b1);
    }

    @Test
    public void test02(){
        String str = "<h1>搜索;\"aas\"ssa</h1><img src=\"sasasa\" />hello</img>";
//        HtmlUtil.unescape(HtmlUtil.filter(str));
        System.out.println(HtmlUtil.unescape(HtmlUtil.filter(str)));
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    static class TestBean {
        private Integer id;
        private String name;
        private String password;
        private String avatar;
        private String address;
    }
}
