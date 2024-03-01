package com.progresssoft.fxdeals.platform.spring.mapper;

import com.progresssoft.fxdeals.domain.model.Deal;
import com.progresssoft.fxdeals.domain.model.DealRequest;
import com.progresssoft.fxdeals.platform.spring.model.DealEntity;

public class DealEntityMapper {

    public static DealEntity convertToEntity(Deal deal) {

        return DealEntity.builder()
                .id(deal.getId())
                .dealTimestamp(deal.getDealTimestamp()).
                dealAmount(deal.getDealAmount()).
                fromCurrencyIsoCode(deal.getFromCurrencyIsoCode()).
                toCurrencyIsoCode(deal.getToCurrencyIsoCode())
                .build();
    }

    public static Deal convertToDeal(DealEntity deal) {

        return Deal.builder()
                .id(deal.getId())
                .dealTimestamp(deal.getDealTimestamp()).
                dealAmount(deal.getDealAmount()).
                fromCurrencyIsoCode(deal.getFromCurrencyIsoCode()).
                toCurrencyIsoCode(deal.getToCurrencyIsoCode())
                .build();
    }

    public static DealEntity convertToDealEntity(DealRequest deal) {

        return DealEntity.builder()
                .id(deal.getId())
                .dealTimestamp(deal.getDealTimestamp()).
                dealAmount(deal.getDealAmount()).
                fromCurrencyIsoCode(deal.getFromCurrencyIsoCode()).
                toCurrencyIsoCode(deal.getToCurrencyIsoCode())
                .build();
    }
}
