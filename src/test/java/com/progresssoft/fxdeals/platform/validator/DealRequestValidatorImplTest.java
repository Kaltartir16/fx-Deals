package com.progresssoft.fxdeals.platform.validator;

import com.progresssoft.fxdeals.domain.exception.CurrencyNotFoundException;
import com.progresssoft.fxdeals.domain.model.DealRequest;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class DealRequestValidatorImplTest {

    @Test
    void validate_ShouldReturnTrue_WhenCurrenciesAreValid() {
        // Given
        DealRequestValidatorImpl validator = new DealRequestValidatorImpl();
        DealRequest dealRequest = new DealRequest();
        dealRequest.setFromCurrencyIsoCode("USD");
        dealRequest.setToCurrencyIsoCode("EUR");
        // When
        boolean result = validator.validate(dealRequest);
        // Then
        assertTrue(result);
    }

    @Test
    void validate_ShouldThrowCurrencyNotFoundException_WhenFromCurrencyIsInvalid() {
        // Given
        DealRequestValidatorImpl validator = new DealRequestValidatorImpl();
        DealRequest dealRequest = new DealRequest();
        dealRequest.setFromCurrencyIsoCode("INVALID");
        // When and Then
        CurrencyNotFoundException exception = assertThrows(CurrencyNotFoundException.class, () -> validator.validate(dealRequest));
        assertEquals("Currency code INVALID Not Found", exception.getMessage());
    }

    @Test
    void validate_ShouldThrowCurrencyNotFoundException_WhenToCurrencyIsInvalid() {
        // Given
        DealRequestValidatorImpl validator = new DealRequestValidatorImpl();
        DealRequest dealRequest = new DealRequest();
        dealRequest.setToCurrencyIsoCode("INVALID");
        dealRequest.setFromCurrencyIsoCode("INVALID");
        // When and Then
        CurrencyNotFoundException exception = assertThrows(CurrencyNotFoundException.class, () -> validator.validate(dealRequest));
        assertEquals("Currency code INVALID Not Found", exception.getMessage());
    }

    @Test
    void validateCurrency_ShouldReturnTrue_WhenCurrencyIsValid() {
        // Given
        DealRequestValidatorImpl validator = new DealRequestValidatorImpl();
        // When
        boolean result = validator.validateCurrency("USD");

        // Then
        assertTrue(result);
    }

    @Test
    void validateCurrency_ShouldThrowCurrencyNotFoundException_WhenCurrencyIsInvalid() {
        // Given
        DealRequestValidatorImpl validator = new DealRequestValidatorImpl();
        // When and Then
        CurrencyNotFoundException exception = assertThrows(CurrencyNotFoundException.class, () -> validator.validateCurrency("INVALID"));
        assertEquals("Currency code INVALID Not Found", exception.getMessage());
    }

    @Test
    void validateCurrency_ShouldThrowCurrencyNotFoundException_WhenCurrencyCodeIsNull() {
        // Given
        DealRequestValidatorImpl validator = new DealRequestValidatorImpl();
        // When and Then
        CurrencyNotFoundException exception = assertThrows(CurrencyNotFoundException.class, () -> validator.validateCurrency(null));
        assertEquals("Currency code null Not Found", exception.getMessage());
    }

    @Test
    void validateCurrency_ShouldThrowCurrencyNotFoundException_WhenCurrencyCodeIsEmpty() {
        // Given
        DealRequestValidatorImpl validator = new DealRequestValidatorImpl();
        // When and Then
        CurrencyNotFoundException exception = assertThrows(CurrencyNotFoundException.class, () -> validator.validateCurrency(""));
        assertEquals("Currency code  Not Found", exception.getMessage());
    }
}
