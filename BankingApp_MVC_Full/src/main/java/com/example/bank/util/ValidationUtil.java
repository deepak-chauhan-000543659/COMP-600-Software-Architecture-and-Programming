package com.example.bank.util;

import java.math.BigDecimal;

public class ValidationUtil {
    public static void requireNonBlank(String value, String field) {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException(field + " is required.");
        }
    }
    public static void requireNonNegative(BigDecimal amount, String field) {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException(field + " must be >= 0.");
        }
    }
    public static void requirePositive(BigDecimal amount, String field) {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException(field + " must be > 0.");
        }
    }
}
