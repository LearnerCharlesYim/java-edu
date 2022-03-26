package com.charles.common.execption;

import cn.hutool.core.util.ClassUtil;
import com.charles.common.domain.R;
import com.charles.common.domain.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

@RestControllerAdvice
@Slf4j
@SuppressWarnings("all")
public class GlobeExceptionHandler {

    /**
     * 处理运行时未知异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(RuntimeException.class)
    private R<Void> runtimeExceptionHandler(RuntimeException e) {
        e.printStackTrace();
        String message = String.format("%s异常;错误信息:%s", ClassUtil.getClassName(e.getClass(), true), e.getMessage());
        return R.fail(message);
    }

    /**
     * 处理Servlet异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler({
            HttpMediaTypeNotSupportedException.class,
            HttpMediaTypeNotAcceptableException.class,
            HttpMessageNotReadableException.class,
            MethodArgumentTypeMismatchException.class,
            MissingServletRequestParameterException.class,
            MissingServletRequestPartException.class,
            TypeMismatchException.class,
            HttpMessageNotWritableException.class,
            MultipartException.class
    })
    private R<Void> handleServletExceptionHandler(Exception e) {
        log.error("Servlet异常: {}", e.getMessage());
        if (e instanceof HttpMediaTypeNotSupportedException || e instanceof MultipartException) {
            return R.fail(ResultCode.REQUEST_MEDIA_TYPE_FAIL.getCode(), getMessage(ResultCode.REQUEST_MEDIA_TYPE_FAIL, e.getMessage()));
        } else if (e instanceof TypeMismatchException) {
            return R.fail(ResultCode.PARAM_TYPE_ERROR);
        } else if (e instanceof HttpMessageNotReadableException) {
            return R.fail(ResultCode.PARAM_IS_BLANK);
        } else if (e instanceof MissingServletRequestParameterException) {
            return R.fail(ResultCode.PARAM_NOT_COMPLETE);
        } else if (e instanceof MissingServletRequestPartException) {
            return R.fail(ResultCode.FILE_NOT_FOUND);
        } else if (e instanceof HttpMessageNotWritableException) {
            return R.fail(ResultCode.RESPONSE_CONVERT_FALI);
        } else if (e instanceof HttpMediaTypeNotAcceptableException) {
            return R.fail(ResultCode.RESPONSE_MEDIA_TYPE_FAIL);
        } else {
            return R.fail(ResultCode.UNKNOWN_ERROR);
        }
    }

    /**
     * 处理自定义业务异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(BizException.class)
    private R<Void> bizExceptionHandler(BizException e) {
        log.error("发送业务异常！原因是：{}", e.getErrorMsg());
        return R.fail(e.getErrorCode(), e.getErrorMsg());
    }

    /**
     * 处理参数验证异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler({
            BindException.class,
            ConstraintViolationException.class,
            MethodArgumentNotValidException.class
    })
    public R<Void> handleParamValidateException(Exception e) {
        if (e instanceof BindException) {
            // ex.getFieldError():随机返回一个对象属性的异常信息。如果要一次性返回所有对象属性异常信息，
            // 则调用ex.getAllErrors()
            FieldError fieldError = ((BindException) e).getFieldError();
            // 生成返回结果
            assert fieldError != null;
            log.error("发送参数验证异常！原因是：{}", fieldError.getDefaultMessage());
            return R.fail(ResultCode.PARAM_VALIDATE_FAIL.getCode(), getMessage(ResultCode.PARAM_VALIDATE_FAIL, fieldError.getDefaultMessage()));

        } else if (e instanceof ConstraintViolationException) {
            ConstraintViolationException constraintViolationException = (ConstraintViolationException) e;
            Set<ConstraintViolation<?>> violations = constraintViolationException.getConstraintViolations();
            String message = violations.stream().map(ConstraintViolation::getMessage).collect(Collectors.joining(";"));
            log.error("发送参数验证异常！原因是：{}", message);
            return R.fail(ResultCode.PARAM_VALIDATE_FAIL.getCode(), getMessage(ResultCode.PARAM_VALIDATE_FAIL, message));

        } else if (e instanceof MethodArgumentNotValidException) {
            FieldError fieldError = ((MethodArgumentNotValidException) e).getBindingResult().getFieldError();
            assert fieldError != null;
            log.error("发送参数验证异常！原因是：{}", fieldError.getDefaultMessage());
            return R.fail(ResultCode.PARAM_VALIDATE_FAIL.getCode(), getMessage(ResultCode.PARAM_VALIDATE_FAIL, fieldError.getDefaultMessage()));
        } else {
            return R.fail(ResultCode.UNKNOWN_ERROR);
        }
    }

    /**
     * 处理请求方法不支持异常
     *
     * @param ex
     * @param request
     * @return
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public R<Void> handlerRequestMethodException(HttpRequestMethodNotSupportedException ex, HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        String method = request.getMethod();
        log.error("{}不支持{}方法", requestURI, method);
        return R.fail(ResultCode.REQUEST_METHOD_NOT_SUPPORTED.getCode(), requestURI + "不支持" + method + "方法");
    }

    private String getMessage(BaseErrorInfoInterface errorInfo, String... message) {
        return errorInfo.getMessage() + ":" + Arrays.stream(message).collect(Collectors.joining(";"));
    }
}
