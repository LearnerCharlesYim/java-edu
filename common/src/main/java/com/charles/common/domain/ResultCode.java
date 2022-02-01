package com.charles.common.domain;

import com.charles.common.execption.BaseErrorInfoInterface;


public enum ResultCode implements BaseErrorInfoInterface {
    /* 成功 */
    SUCCESS(200, "操作成功"),

    /* 默认失败 */
    COMMON_FAIL(-1, "操作失败"),

    /* 参数错误：1000～1999 */
    PARAM_NOT_VALID(1001, "参数无效"),
    PARAM_IS_BLANK(1002, "参数为空"),
    PARAM_TYPE_ERROR(1003, "参数类型错误"),
    PARAM_NOT_COMPLETE(1004, "参数缺失"),

    /* 用户错误 */
    USER_NOT_LOGIN(2001, "用户未登录"),
    USER_ACCOUNT_EXPIRED(2002, "账号已过期"),
    USER_CREDENTIALS_ERROR(2003, "账号或密码错误"),
    USER_CREDENTIALS_EXPIRED(2004, "密码过期"),
    TOKEN_NOT_PROVIDE(2005, "未提供token"),
    TOKEN_INVALID(2006, "token无效"),
    TOKEN_EXPIRED(2007, "token已过期"),
    TOKEN_BE_REPLACED(2008, "token已被顶下线"),
    TOKEN_KICK_OUT(2009, "token已被踢下线"),
    INCONSISTENT_PASSWORD(2010, "两次密码输入不一致"),

    /* 业务错误 */
    NO_PERMISSION(3001, "没有操作权限"),

    EMAIL_EXPIRED(4001, "邮箱验证码过期"),
    EMAIL_ERROR(4002, "邮箱验证码错误"),
    EMAIL_SEND_ERROR(4003, "邮箱远程连接失败，请稍后再试"),

    GRAPH_CAPTCHA_EXPIRED(4003, "图形验证码过期"),
    GRAPH_CAPTCHA_ERROR(4004, "图形验证码错误"),
    BOARD_NOT_FOUND(4005, "板块未找到"),

    FILE_NOT_FOUND(4006, "文件空指针异常"),
    FILE_FORMAT_ERROR(4007, "文件格式错误"),

    TEACHER_NOT_FOUND(4008,"教师未找到"),

    /* 系统错误 */
    UNKNOWN_ERROR(5000, "接口未知异常，请稍后再试"),
    SERVER_BUSY(50001, "服务器正忙，请稍后再试！");

    private Integer code;
    private String message;

    ResultCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
