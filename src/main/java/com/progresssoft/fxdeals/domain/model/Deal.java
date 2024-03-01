package com.progresssoft.fxdeals.domain.model;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
@Builder
@Data
public class Deal {

    private String id;
    private String fromCurrencyIsoCode;
    private String toCurrencyIsoCode;
    private LocalDateTime dealTimestamp;
    private BigDecimal dealAmount;


}

