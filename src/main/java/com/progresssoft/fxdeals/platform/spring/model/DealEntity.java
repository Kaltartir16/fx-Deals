package com.progresssoft.fxdeals.platform.spring.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
@Data
@Entity
public class DealEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    private String fromCurrencyIsoCode;
    private String toCurrencyIsoCode;
    private LocalDateTime dealTimestamp;
    private BigDecimal dealAmount;


}

