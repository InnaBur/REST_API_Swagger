package com.spring.firstSpring.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ FIELD })
@Retention(RUNTIME)
@Constraint(validatedBy = IpnValidator.class)
@Documented
public @interface IPN {

    String message() default "{IPN is invalid}";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

}
