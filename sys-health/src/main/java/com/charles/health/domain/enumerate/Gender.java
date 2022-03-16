package com.charles.health.domain.enumerate;

public enum Gender {
    UNLIMITED(0,"不限"),
    MALE(1,"男"),// 男
    FEMAIL(2,"女");// 女

    private Integer code;
    private String sex;

    Gender(Integer code,String sex) {
        this.code = code;
        this.sex = sex;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}