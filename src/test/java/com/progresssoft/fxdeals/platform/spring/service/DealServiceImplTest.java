package com.progresssoft.fxdeals.platform.spring.service;

import com.progresssoft.fxdeals.domain.exception.DuplicateDealException;
import com.progresssoft.fxdeals.domain.model.Deal;
import com.progresssoft.fxdeals.domain.model.DealRequest;
import com.progresssoft.fxdeals.domain.validator.DealRequestValidator;
import com.progresssoft.fxdeals.domain.validator.DealValidator;
import com.progresssoft.fxdeals.platform.repository.DealJpaRepository;
import com.progresssoft.fxdeals.platform.spring.model.DealEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class DealServiceImplTest {

    @Mock
    private DealJpaRepository dealsRepository;

    @Mock
    private DealValidator dealValidator;

    @Mock
    private DealRequestValidator dealRequestValidator;

    @InjectMocks
    private DealServiceImpl dealService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void importDeal_ShouldSaveValidDeal_WhenDealDoesNotExist() {
        // Given
        Deal validDeal = Deal.builder()
                .id("dealId")
                .fromCurrencyIsoCode("USD")
                .toCurrencyIsoCode("EUR")
                .dealTimestamp(LocalDateTime.now())
                .dealAmount(BigDecimal.TEN)
                .build();
        when(dealsRepository.existsById(any())).thenReturn(false);

        when(dealsRepository.save(any(DealEntity.class))).thenReturn(DealEntity.builder()
                .id("dealId")
                .fromCurrencyIsoCode("USD")
                .toCurrencyIsoCode("EUR")
                .dealTimestamp(LocalDateTime.now())
                .dealAmount(BigDecimal.TEN)
                .build());


        // When
        Deal result = dealService.importDeal(validDeal);

        // Then
        verify(dealValidator).validate(validDeal);
        verify(dealsRepository).existsById(validDeal.getId());
        verify(dealsRepository).save(any());
        assertNotNull(result);
        // Add additional assertions based on your specific requirements
    }

    @Test
    void importDeal_ShouldThrowDuplicateDealException_WhenDealExists() {
        // Given
        Deal existingDeal = Deal.builder()
                .id("existingDealId")
                .fromCurrencyIsoCode("USD")
                .toCurrencyIsoCode("EUR")
                .dealTimestamp(LocalDateTime.now())
                .dealAmount(BigDecimal.TEN)
                .build();
        when(dealsRepository.existsById(any())).thenReturn(true);

        // When and Then
        DuplicateDealException exception = assertThrows(DuplicateDealException.class, () -> dealService.importDeal(existingDeal));
        assertEquals("Deal with ID " + existingDeal.getId() + " is already processed.", exception.getMessage());
        verify(dealValidator).validate(existingDeal);
        verify(dealsRepository).existsById(existingDeal.getId());
        verify(dealsRepository, never()).save(any());
        // Add additional assertions based on your specific requirements
    }

    @Test
    void importDealRequest_ShouldSaveValidDealRequest_WhenDealRequestDoesNotExist() {
        // Given
        DealRequest validDealRequest = new DealRequest();
        validDealRequest.setId("requestId");
        validDealRequest.setFromCurrencyIsoCode("USD");
        validDealRequest.setToCurrencyIsoCode("EUR");
        validDealRequest.setDealTimestamp(LocalDateTime.now());
        validDealRequest.setDealAmount(BigDecimal.TEN);

        when(dealsRepository.existsById(any())).thenReturn(false);


        when(dealsRepository.save(any(DealEntity.class))).thenReturn(DealEntity.builder()
                .id("dealId")
                .fromCurrencyIsoCode("USD")
                .toCurrencyIsoCode("EUR")
                .dealTimestamp(LocalDateTime.now())
                .dealAmount(BigDecimal.TEN)
                .build());
        // When
        Deal result = dealService.importDeal(validDealRequest);

        // Then
        verify(dealRequestValidator).validate(validDealRequest);
        verify(dealsRepository).existsById(validDealRequest.getId());
        verify(dealsRepository).save(any());
        assertNotNull(result);
        // Add additional assertions based on your specific requirements
    }

    @Test
    void importDealRequest_ShouldThrowDuplicateDealException_WhenDealRequestExists() {
        // Given
        DealRequest existingDealRequest = new DealRequest();
        existingDealRequest.setId("existingRequestId");
        existingDealRequest.setFromCurrencyIsoCode("USD");
        existingDealRequest.setToCurrencyIsoCode("EUR");
        existingDealRequest.setDealTimestamp(LocalDateTime.now());
        existingDealRequest.setDealAmount(BigDecimal.TEN);

        when(dealsRepository.existsById(any())).thenReturn(true);

        // When and Then
        DuplicateDealException exception = assertThrows(DuplicateDealException.class, () -> dealService.importDeal(existingDealRequest));
        assertEquals("Deal with ID " + existingDealRequest.getId() + " is already processed.", exception.getMessage());
        verify(dealRequestValidator).validate(existingDealRequest);
        verify(dealsRepository).existsById(existingDealRequest.getId());
        verify(dealsRepository, never()).save(any());
        // Add additional assertions based on your specific requirements
    }

    // Add more test cases as needed to cover all scenarios
}
