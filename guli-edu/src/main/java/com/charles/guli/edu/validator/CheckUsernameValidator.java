package com.charles.guli.edu.validator;

import cn.hutool.extra.spring.SpringUtil;
import com.charles.guli.edu.domain.pojo.User;
import com.charles.guli.edu.repository.UserRepository;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CheckUsernameValidator implements ConstraintValidator<CheckUsername, String> {

    private UserRepository userRepository;

    @Override
    public void initialize(CheckUsername constraintAnnotation) {
        this.userRepository = SpringUtil.getBean(UserRepository.class);
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if (!StringUtils.hasText(s)) return false;
        User user = userRepository.findByUsername(s);
        return user == null;
    }
}
