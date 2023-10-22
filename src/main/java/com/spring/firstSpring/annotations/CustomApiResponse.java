package com.spring.firstSpring.annotations;

import com.spring.firstSpring.dto.PersonDTO;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.ANNOTATION_TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface CustomApiResponse {
    String responseCode() default "";

    String responseDescription() default "";

    String mediaType() default "application/json";

    Class<?> schema() default PersonDTO.class;
}

