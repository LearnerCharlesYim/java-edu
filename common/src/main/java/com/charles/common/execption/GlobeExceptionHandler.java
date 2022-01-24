package com.charles.common.execption;

import com.charles.common.domain.R;
import com.charles.common.domain.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;

@RestControllerAdvice
@Slf4j
public class GlobeExceptionHandler {

    @ExceptionHandler(BizException.class)
    private R<Void> bizExceptionHandler(BizException e) {
        log.error("发送业务异常！原因是：{}", e.getErrorMsg());
        return R.fail(e.getErrorCode(), e.getErrorMsg());
    }

    @ExceptionHandler({BindException.class,
            ConstraintViolationException.class,
            MethodArgumentNotValidException.class})
    public R<Void> handleParamValidateException(Exception e) {
        if (e instanceof BindException) {
            // ex.getFieldError():随机返回一个对象属性的异常信息。如果要一次性返回所有对象属性异常信息，
            // 则调用ex.getAllErrors()
            FieldError fieldError = ((BindException) e).getFieldError();
            // 生成返回结果
            assert fieldError != null;
            log.error("发送参数验证异常！原因是：{}", fieldError.getDefaultMessage());
            return R.fail(ResultCode.PARAM_NOT_VALID.getCode(), fieldError.getDefaultMessage());
        } else if (e instanceof ConstraintViolationException) {
            log.error("发送参数验证异常！原因是：{}", e.getMessage().split(": ")[1]);
            return R.fail(ResultCode.PARAM_NOT_VALID.getCode(), e.getMessage().split(": ")[1]);
        } else {
            FieldError fieldError = ((MethodArgumentNotValidException) e).getBindingResult().getFieldError();
            log.error("发送参数验证异常！原因是：{}", fieldError.getDefaultMessage());
            return R.fail(ResultCode.PARAM_NOT_VALID.getCode(), fieldError.getDefaultMessage());
        }
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public R<Void> handlerRequestMethodException(HttpRequestMethodNotSupportedException ex, HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        String method = request.getMethod();
        log.error("{}不支持{}方法", requestURI, method);
        return R.fail().message(requestURI + "不支持" + method + "方法");
    }

    @ExceptionHandler({
            HttpMediaTypeNotSupportedException.class,
            HttpMessageNotReadableException.class,
            MethodArgumentTypeMismatchException.class
    })
    private R<Void> paramExceptionHandler(Exception e) {
        if (e instanceof HttpMediaTypeNotSupportedException) {
            return R.fail(ResultCode.PARAM_TYPE_ERROR);
        } else if (e instanceof MethodArgumentTypeMismatchException) {
            return R.fail(ResultCode.PARAM_NOT_VALID);
        } else {
            return R.fail(ResultCode.PARAM_NOT_COMPLETE);
        }
    }
}
