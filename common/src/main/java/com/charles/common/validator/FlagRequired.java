package com.charles.common.validator;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * 用于验证状态是否在指定范围内的注解
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Constraint(validatedBy = FlagRequired.FlagRequiredValidator.class)
public @interface FlagRequired {
    String[] value() default {};

    String message() default "flag is not found";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    class FlagRequiredValidator implements ConstraintValidator<FlagRequired, Integer> {
        private String[] values;

        @Override
        public void initialize(FlagRequired flagValidator) {
            this.values = flagValidator.value();
        }

        @Override
        public boolean isValid(Integer value, ConstraintValidatorContext constraintValidatorContext) {
            boolean isValid = false;
            if (value == null) {
                //当状态为空时使用默认值
                return true;
            }
            for (String s : values) {
                if (s.equals(String.valueOf(value))) {
                    isValid = true;
                    break;
                }
            }
            return isValid;
        }
    }
}
