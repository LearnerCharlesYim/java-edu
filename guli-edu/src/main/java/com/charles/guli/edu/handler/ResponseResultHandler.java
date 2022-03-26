package com.charles.guli.edu.handler;

import cn.hutool.json.JSONUtil;
import com.charles.common.annotation.NoResponseResult;
import com.charles.common.annotation.ResponseResult;
import com.charles.common.domain.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Objects;

@Slf4j
@ControllerAdvice
public class ResponseResultHandler implements ResponseBodyAdvice<Object> {
    public static final String RESPONSE_RESULT_ANN = "RESPONSE-RESULT-ANN";

    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
//        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
//        ResponseResult responseResultAnn = (ResponseResult) request.getAttribute(RESPONSE_RESULT_ANN);
//        return responseResultAnn != null;
        Method method = methodParameter.getMethod();
        if (method == null) return true;

        if (method.isAnnotationPresent(NoResponseResult.class)) return false;

        return methodParameter.getDeclaringClass().isAnnotationPresent(ResponseResult.class)
                || method.isAnnotationPresent(ResponseResult.class);
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest request, ServerHttpResponse response) {
        if (body instanceof String) {
            response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
            return JSONUtil.toJsonStr(R.ok(body));
        } else if (body instanceof R) {
            return body;
        }
        return R.ok(body);
    }
}
