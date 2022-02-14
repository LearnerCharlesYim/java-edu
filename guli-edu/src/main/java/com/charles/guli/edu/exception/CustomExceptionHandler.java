package com.charles.guli.edu.exception;

import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.exception.NotPermissionException;
import com.charles.common.domain.R;
import com.charles.common.domain.ResultCode;
import com.charles.common.execption.GlobeExceptionHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class CustomExceptionHandler extends GlobeExceptionHandler {

    // 全局异常拦截（拦截项目中的NotLoginException异常）
    @ExceptionHandler(NotLoginException.class)
    public R<Void> RestHandlerNotLoginException(NotLoginException nle) throws Exception {
        ResultCode rc;
        // 判断场景值，定制化异常信息
        switch (nle.getType()) {
            case NotLoginException.NOT_TOKEN:
                rc = ResultCode.TOKEN_NOT_PROVIDE;
                break;
            case NotLoginException.INVALID_TOKEN:
                rc = ResultCode.TOKEN_INVALID;
                break;
            case NotLoginException.TOKEN_TIMEOUT:
                rc = ResultCode.TOKEN_EXPIRED;
                break;
            case NotLoginException.BE_REPLACED:
                rc = ResultCode.TOKEN_BE_REPLACED;
                break;
            case NotLoginException.KICK_OUT:
                rc = ResultCode.TOKEN_KICK_OUT;
                break;
            default:
                rc = ResultCode.USER_NOT_LOGIN;
                break;
        }
        // 返回给前端
        return R.fail(rc);
    }

    @ExceptionHandler(NotPermissionException.class)
    public R<Void> RestHandlerNotPermissionException(NotPermissionException npe) throws Exception {
        return R.fail(ResultCode.NO_PERMISSION);
    }
}
