package com.charles.common.test;

import com.charles.common.utils.enums.EnumUtil;
import com.charles.common.utils.enums.NameValueEnum;
import org.junit.Test;

import java.util.Arrays;

public class TestEnum {

    enum Gender implements NameValueEnum<Integer> {
        MAM(1, "男"),
        FEMAIL(2, "女")
        ;

        private final Integer value;
        private final String name;

        Gender(Integer value, String name) {
            this.value = value;
            this.name = name;
        }

        @Override
        public String getName() {
            return name;
        }

        @Override
        public Integer getValue() {
            return value;
        }
    }

    @Test
    public void test01() {
        Gender gender = EnumUtil.getEnumByValue(Gender.class, 2);
        System.out.println(gender);

        Gender gender1 = EnumUtil.getEnumByValue(new Gender[]{Gender.MAM, Gender.FEMAIL}, 1);
        System.out.println(gender1);

        String name = EnumUtil.getNameByValue(new Gender[]{Gender.MAM, Gender.FEMAIL}, 2);
        System.out.println(name);

        System.out.println(EnumUtil.getValueByName(new Gender[]{Gender.MAM, Gender.FEMAIL}, "女"));

        System.out.println(Arrays.toString(Gender.class.getEnumConstants()));
    }

    @Test
    public void test02(){
        System.out.println(EnumUtil.getNameByValue(Gender.class, 1));
        System.out.println(EnumUtil.getValueByName(Gender.class, "女"));
        System.out.println(EnumUtil.getEnumByValue(Gender.class, 1).getName());
    }
}
