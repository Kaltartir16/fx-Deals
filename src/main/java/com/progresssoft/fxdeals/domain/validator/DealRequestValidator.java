package com.progresssoft.fxdeals.domain.validator;

import com.progresssoft.fxdeals.domain.model.DealRequest;

public interface DealRequestValidator {
    boolean validate(DealRequest dealRequest);

}
