package com.spring.firstSpring;

import com.spring.firstSpring.validation.IpnValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class IpnValidatorTest {

    ConstraintValidatorContext context = mock(ConstraintValidatorContext.class);


    @Test
    void ifIpnIsInvalidByNumberOfDigits() {
        IpnValidator ipnValidator = new IpnValidator();
        boolean expected = ipnValidator.isValid("12", context);
        assertFalse(expected);

    }

    @Test
    void ifIpnIsValidByNumberOfDigits() {

        IpnValidator ipnValidator = new IpnValidator();

        boolean expected = ipnValidator.isValid("1111111117", context);
        assertTrue(expected);
    }

    @Test
    void ifIpnIsInvalidByControlDigit() {

        IpnValidator ipnValidator = new IpnValidator();

        assertFalse(ipnValidator.isValid("1111111110", context));
        assertFalse(ipnValidator.isValid("1111111111", context));
        assertFalse(ipnValidator.isValid("1111111112", context));
        assertFalse(ipnValidator.isValid("1111111113", context));
        assertFalse(ipnValidator.isValid("1111111114", context));
        assertFalse(ipnValidator.isValid("1111111115", context));
        assertFalse(ipnValidator.isValid("1111111116", context));
        assertFalse(ipnValidator.isValid("1111111118", context));
        assertFalse(ipnValidator.isValid("1111111119", context));


    }

}