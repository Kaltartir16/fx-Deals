package com.bloomberg.fxdealanalyzer.service;


import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.bloomberg.fxdealanalyzer.exception.DealAlreadyProcessedException;
import com.bloomberg.fxdealanalyzer.model.FXDealEntity;
import com.bloomberg.fxdealanalyzer.model.FXDealRequest;
import com.bloomberg.fxdealanalyzer.repository.FXDealRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class FXDealServiceTest {

    @InjectMocks
    private FXDealService fxDealService;

    @Mock
    private FXDealRepository fxDealRepository;

    private FXDealEntity fxDealEntity;
    private FXDealRequest fxDealRequest;

    @BeforeEach
    public void setUp() {
        fxDealEntity = new FXDealEntity();
        fxDealEntity.setDealUniqueId("uniqueId123");

        fxDealRequest = new FXDealRequest();
        fxDealRequest.setDealUniqueId("uniqueId123");
    }


    @Test
    public void processFXDeal_ShouldThrowException_WhenDealAlreadyProcessed() {
        // Given
        when(fxDealRepository.existsByDealUniqueId(fxDealEntity.getDealUniqueId())).thenReturn(true);

        // When & Then
        assertThrows(DealAlreadyProcessedException.class, () -> fxDealService.processFXDeal(fxDealEntity));
    }

    @Test
    public void processFXDeal_ShouldSaveDeal_WhenDealIsNew() {
        // Given
        when(fxDealRepository.existsByDealUniqueId(fxDealEntity.getDealUniqueId())).thenReturn(false);
        when(fxDealRepository.save(fxDealEntity)).thenReturn(fxDealEntity);

        // When
        FXDealEntity savedEntity = fxDealService.processFXDeal(fxDealEntity);

        // Then
        assertEquals(fxDealEntity, savedEntity);
        verify(fxDealRepository, times(1)).save(fxDealEntity);
    }

    @Test
    public void processFXDealRequest_ShouldThrowException_WhenDealAlreadyProcessed() {
        // Given
        when(fxDealRepository.existsByDealUniqueId(fxDealRequest.getDealUniqueId())).thenReturn(true);

        // When & Then
        assertThrows(DealAlreadyProcessedException.class, () -> fxDealService.processFXDeal(fxDealRequest));
    }

    @Test
    public void processFXDealRequest_ShouldSaveDeal_WhenDealIsNew() {
        // Given
        when(fxDealRepository.existsByDealUniqueId(fxDealRequest.getDealUniqueId())).thenReturn(false);

        FXDealEntity convertedEntity = new FXDealEntity();
        when(fxDealRepository.save(any(FXDealEntity.class))).thenReturn(convertedEntity);

        // When
        FXDealEntity savedEntity = fxDealService.processFXDeal(fxDealRequest);

        // Then
        assertNotNull(savedEntity);
        verify(fxDealRepository, times(1)).save(any(FXDealEntity.class));
    }

}

