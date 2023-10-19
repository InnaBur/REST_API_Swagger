package com.spring.firstSpring.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class IpnValidator implements ConstraintValidator<IPN, String> {

    private final int[] FORMULA_DIGITS = {-1, 5, 7, 9, 4, 6, 10, 5, 7};

    @Override
    public void initialize(IPN ipn) {
    }
    @Override
    public boolean isValid(String ipn, ConstraintValidatorContext constraintValidatorContext) {

        if (ipn == null || !ipn.matches("\\d{10}")) {
            return false;
        }

        int controlSum = calculateControlSum(ipn);
        int controlDigit = (controlSum % 11) % 10;
        int lastDigit = Character.getNumericValue(ipn.charAt(9));
        return controlDigit == lastDigit;
    }

    private int calculateControlSum(String ipn) {
        int controlSum = 0;
        for (int i = 0; i < ipn.length() - 1; i++) {
            controlSum += ipn.charAt(i) * FORMULA_DIGITS[i];
        }
        return controlSum;
    }

}
