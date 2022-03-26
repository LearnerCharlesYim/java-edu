package com.charles.guli.edu.domain.vo;

import com.charles.common.utils.enums.NameValueEnum;
import com.charles.common.validator.EnumRequired;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
public class TestVo {

    @EnumRequired(value = Gender.class, message = "性别参数错误")
    private Integer gender;

    @NotBlank(message = "姓名不为空")
    private String name;

    private List<TestVo> children;

    public enum Gender implements NameValueEnum<Integer> {
        MAN(1, "男"),
        WOMAN(2, "女");

        Gender(Integer value, String name) {
            this.value = value;
            this.name = name;
        }

        private final Integer value;
        private final String name;

        @Override
        public String getName() {
            return name;
        }

        @Override
        public Integer getValue() {
            return value;
        }
    }
}
