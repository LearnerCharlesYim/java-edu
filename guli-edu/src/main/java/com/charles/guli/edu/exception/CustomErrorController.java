package com.charles.guli.edu.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorViewResolver;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@Slf4j
public class CustomErrorController extends BasicErrorController {

    @Autowired
    public CustomErrorController(ErrorAttributes errorAttributes,
                                 ServerProperties errorProperties,
                                 List<ErrorViewResolver> errorViewResolvers) {

        super(errorAttributes, errorProperties.getError(), errorViewResolvers);
    }

    @Override
    public ResponseEntity<Map<String, Object>> error(HttpServletRequest request) {
        HttpStatus status = getStatus(request);
        Map<String, Object> map = new HashMap<>();
        map.put("success", false);
        map.put("code", status.value());
        map.put("message", status.getReasonPhrase());
        map.put("data", null);
        log.error("{} {} 异常", status.value(), status.getReasonPhrase());
        return new ResponseEntity<>(map, status);
    }
}
