package com.progresssoft.fxdeals.platform.validator;

import com.progresssoft.fxdeals.domain.exception.CurrencyNotFoundException;
import com.progresssoft.fxdeals.domain.model.Deal;
import com.progresssoft.fxdeals.domain.validator.DealValidator;

import java.util.Currency;

public class DealValidatorImpl implements DealValidator {
    @Override
    public boolean validate(Deal deal) {
        return validateCurrency(deal.getFromCurrencyIsoCode()) && validateCurrency(deal.getToCurrencyIsoCode());
    }

    public boolean validateCurrency(String currencyCode) {
        if (currencyCode == null || currencyCode.isEmpty()) {
            throw new CurrencyNotFoundException("Currency code " + currencyCode + " Not Found");
        }
        try {
            Currency.getInstance(currencyCode);
            return true;
        } catch (IllegalArgumentException e) {
            throw new CurrencyNotFoundException("Currency code " + currencyCode + " Not Found");

        }
    }


}
