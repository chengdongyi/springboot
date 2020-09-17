package com.example.annotation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.ANNOTATION_TYPE})
@Constraint(validatedBy = FixedValueValidator.class)
public @interface FixedValue {

    String[] params();

    String message() default "参数非法";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
