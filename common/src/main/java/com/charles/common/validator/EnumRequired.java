package com.charles.common.validator;

import com.charles.common.utils.enums.ValueEnum;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.PARAMETER, ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = EnumRequired.EnumRequiredValidator.class)
public @interface EnumRequired {

    Class<? extends Enum<? extends ValueEnum>> value() default EnumRequired.DefaultEnum.class;

    String message() default "";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    @SuppressWarnings("all")
    class EnumRequiredValidator implements ConstraintValidator<EnumRequired, Object> {

        private Class<? extends Enum<? extends ValueEnum>> clazz;

        @Override
        public void initialize(EnumRequired constraintAnnotation) {
            this.clazz = constraintAnnotation.value();
        }

        @Override
        public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
            for (Enum<? extends ValueEnum> e : clazz.getEnumConstants()) {
                if (((ValueEnum) e).getValue().equals(o)) {
                    return true;
                }
            }
            return false;
        }
    }

    enum DefaultEnum implements ValueEnum<Integer> {
        ;

        @Override
        public Integer getValue() {
            return 0;
        }
    }
}
