package com.bloomberg.fxdealanalyzer.validator;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CurrencyCodeValidatorTest {

    private CurrencyCodeValidator validator;

    @BeforeEach
    void setUp() {
        validator = new CurrencyCodeValidator();
    }

    @Test
    void isValid_ShouldReturnTrue_WhenCurrencyCodeIsValid() {
        // Given
        String validCurrencyCode = "USD";

        // When
        boolean result = validator.isValid(validCurrencyCode, null);

        // Then
        assertTrue(result, "Validator should return true for a valid currency code");
    }

    @Test
    void isValid_ShouldReturnFalse_WhenCurrencyCodeIsInvalid() {
        // Given
        String invalidCurrencyCode = "XYZ";

        // When
        boolean result = validator.isValid(invalidCurrencyCode, null);

        // Then
        assertFalse(result, "Validator should return false for an invalid currency code");
    }

    @Test
    void isValid_ShouldReturnTrue_WhenCurrencyCodeIsNull() {
        // Given
        String nullCurrencyCode = null;

        // When
        boolean result = validator.isValid(nullCurrencyCode, null);

        // Then
        assertTrue(result, "Validator should return true for null currency code as @NotNull should handle null case");
    }
}
