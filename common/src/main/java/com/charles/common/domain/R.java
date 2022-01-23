package com.charles.common.domain;

import com.charles.common.execption.BaseErrorInfoInterface;

import java.util.HashMap;
import java.util.Map;

@SuppressWarnings({"unchecked","all"})
public class R {
    private Boolean success;
    private Integer code;
    private String message;
    private Object data;

    public R() {
    }

    public R(BaseErrorInfoInterface errorInfo) {
        this.code = errorInfo.getCode();
        this.message = errorInfo.getMessage();
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public static R ok() {
        R r = new R();
        r.setSuccess(true);
        r.setCode(ResultCode.SUCCESS.getCode());
        r.setMessage(ResultCode.SUCCESS.getMessage());
        return r;
    }

    public static R ok(Object data) {
        R r = new R();
        r.setSuccess(true);
        r.setCode(ResultCode.SUCCESS.getCode());
        r.setMessage(ResultCode.SUCCESS.getMessage());
        r.setData(data);
        return r;
    }

    public static R fail() {
        R r = new R();
        r.setSuccess(false);
        r.setCode(ResultCode.COMMON_FAIL.getCode());
        r.setMessage(ResultCode.COMMON_FAIL.getMessage());
        return r;
    }

    public static R fail(BaseErrorInfoInterface errorInfo) {
        R r = new R();
        r.setSuccess(false);
        r.setCode(errorInfo.getCode());
        r.setMessage(errorInfo.getMessage());
        return r;
    }

    public static R fail(Integer code, String message) {
        R r = new R();
        r.setSuccess(false);
        r.setCode(code);
        r.setMessage(message);
        return r;
    }

    public R data(Object data) {
        this.setData(data);
        return this;
    }

    public R data(String key, Object obj) {
        if (!(data instanceof Map))
            this.data = new HashMap<String, Object>();
        ((Map<String, Object>) data).put(key, obj);
        return this;
    }

    public R message(String message) {
        this.setMessage(message);
        return this;
    }

    public R code(Integer code) {
        this.setCode(code);
        return this;
    }
}
