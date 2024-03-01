package com.progresssoft.fxdeals.platform.validator;

import com.progresssoft.fxdeals.domain.exception.CurrencyNotFoundException;
import com.progresssoft.fxdeals.domain.model.DealRequest;
import com.progresssoft.fxdeals.domain.validator.DealRequestValidator;

import java.util.Currency;

public class DealRequestValidatorImpl implements DealRequestValidator {
    @Override
    public boolean validate(DealRequest dealRequest) {
        return  validateCurrency(dealRequest.getFromCurrencyIsoCode()) && validateCurrency(dealRequest.getToCurrencyIsoCode());

    }

    public boolean validateCurrency(String currencyCode)  {
        if (currencyCode == null || currencyCode.isEmpty()) {
            throw new CurrencyNotFoundException("Currency code "+ currencyCode + " Not Found");
        }
        try {
            Currency.getInstance(currencyCode);
            return true;
        } catch (IllegalArgumentException e) {
            throw new CurrencyNotFoundException("Currency code "+ currencyCode + " Not Found");
        }
    }
}
