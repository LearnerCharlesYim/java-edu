package com.charles.common.domain;

import com.charles.common.execption.BaseErrorInfoInterface;

@SuppressWarnings({"unchecked", "all"})
public class R<T> {

    private Boolean success;
    private Integer code;
    private String message;
    private T data;

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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public static R<Void> ok() {
        R<Void> r = new R<>();
        r.setSuccess(true);
        r.setCode(ResultCode.SUCCESS.getCode());
        r.setMessage(ResultCode.SUCCESS.getMessage());
        return r;
    }

    public static <T> R<T> ok(T data) {
        R<T> r = new R<>();
        r.setSuccess(true);
        r.setCode(ResultCode.SUCCESS.getCode());
        r.setMessage(ResultCode.SUCCESS.getMessage());
        r.setData(data);
        return r;
    }

    public static R<Void> fail() {
        R<Void> r = new R<>();
        r.setSuccess(false);
        r.setCode(ResultCode.COMMON_FAIL.getCode());
        r.setMessage(ResultCode.COMMON_FAIL.getMessage());
        return r;
    }

    public static R<Void> fail(BaseErrorInfoInterface errorInfo) {
        R<Void> r = new R<>();
        r.setSuccess(false);
        r.setCode(errorInfo.getCode());
        r.setMessage(errorInfo.getMessage());
        return r;
    }

    public static R<Void> fail(Integer code, String message) {
        R<Void> r = new R<>();
        r.setSuccess(false);
        r.setCode(code);
        r.setMessage(message);
        return r;
    }

    public static R<Void> fail(String message) {
        return fail(ResultCode.COMMON_FAIL.getCode(), message);
    }

    public R<T> data(T data) {
        this.setData(data);
        return this;
    }

    public R<T> message(String message) {
        this.setMessage(message);
        return this;
    }

    public R<T> code(Integer code) {
        this.setCode(code);
        return this;
    }
}
