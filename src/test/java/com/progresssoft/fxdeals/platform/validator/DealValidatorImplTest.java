package com.progresssoft.fxdeals.platform.validator;

import com.progresssoft.fxdeals.domain.exception.CurrencyNotFoundException;
import com.progresssoft.fxdeals.domain.model.Deal;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DealValidatorImplTest {

    @Test
    void validate_ShouldReturnTrue_WhenCurrenciesAreValid() {
        // Given
        DealValidatorImpl validator = new DealValidatorImpl();
        Deal deal = Deal.builder().build();
        deal.setFromCurrencyIsoCode("USD");
        deal.setToCurrencyIsoCode("EUR");

        // When
        boolean result = validator.validate(deal);

        // Then
        assertTrue(result);
    }

    @Test
    void validate_ShouldThrowCurrencyNotFoundException_WhenFromCurrencyIsInvalid() {
        // Given
        DealValidatorImpl validator = new DealValidatorImpl();
        Deal deal =  Deal.builder().build();
        deal.setFromCurrencyIsoCode("INVALID");

        // When and Then
        CurrencyNotFoundException exception = assertThrows(CurrencyNotFoundException.class, () -> validator.validate(deal));
        assertEquals("Currency code INVALID Not Found", exception.getMessage()); // Update this line
    }

    @Test
    void validate_ShouldThrowCurrencyNotFoundException_WhenToCurrencyIsInvalid() {
        // Given
        DealValidatorImpl validator = new DealValidatorImpl();
        Deal deal = Deal.builder().build();
        deal.setToCurrencyIsoCode("INVALID");
        deal.setFromCurrencyIsoCode("INVALID");

        // When and Then
        CurrencyNotFoundException exception = assertThrows(CurrencyNotFoundException.class, () -> validator.validate(deal));
        assertEquals("Currency code INVALID Not Found", exception.getMessage());
    }

    @Test
    void validateCurrency_ShouldReturnTrue_WhenCurrencyIsValid() {
        // Given
        DealValidatorImpl validator = new DealValidatorImpl();

        // When
        boolean result = validator.validateCurrency("USD");

        // Then
        assertTrue(result);
    }

    @Test
    void validateCurrency_ShouldThrowCurrencyNotFoundException_WhenCurrencyIsInvalid() {
        // Given
        DealValidatorImpl validator = new DealValidatorImpl();

        // When and Then
        CurrencyNotFoundException exception = assertThrows(CurrencyNotFoundException.class, () -> validator.validateCurrency("INVALID"));
        assertEquals("Currency code INVALID Not Found", exception.getMessage());
    }

    @Test
    void validateCurrency_ShouldThrowCurrencyNotFoundException_WhenCurrencyCodeIsNull() {
        // Given
        DealValidatorImpl validator = new DealValidatorImpl();

        // When and Then
        CurrencyNotFoundException exception = assertThrows(CurrencyNotFoundException.class, () -> validator.validateCurrency(null));
        assertEquals("Currency code null Not Found", exception.getMessage());
    }

    @Test
    void validateCurrency_ShouldThrowCurrencyNotFoundException_WhenCurrencyCodeIsEmpty() {
        // Given
        DealValidatorImpl validator = new DealValidatorImpl();

        // When and Then
        CurrencyNotFoundException exception = assertThrows(CurrencyNotFoundException.class, () -> validator.validateCurrency(""));
        assertEquals("Currency code  Not Found", exception.getMessage());
    }
}
