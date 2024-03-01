package com.bloomberg.fxdealanalyzer.controller;

import com.bloomberg.fxdealanalyzer.model.FXDealEntity;
import com.bloomberg.fxdealanalyzer.service.FXDealService;
import com.google.common.flogger.FluentLogger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.ResourceUtils;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(MockitoExtension.class)
class FXDealControllerTest {



    @Mock
    private FXDealService fxDealService;

    @Mock
    private FluentLogger logger;
    private FXDealController fxDealController;

    private MockMvc mockMvc;


    @BeforeEach
    void setUp() {

        fxDealController = new FXDealController(fxDealService);
        mockMvc = MockMvcBuilders.standaloneSetup(fxDealController).build();
    }

    @Test
    void createFXDeal_ShouldReturnSavedDeal_WhenValidRequest() throws Exception {
        Path fxDealsPath = ResourceUtils.getFile("classpath:fxDeal.json").toPath();
        String fxDealsJson = new String(Files.readAllBytes(fxDealsPath));

        FXDealEntity savedDeal = new FXDealEntity();

        when(fxDealService.processFXDeal(any(FXDealEntity.class))).thenReturn(savedDeal);
        mockMvc.perform(post("/api/fxdeals")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(fxDealsJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.dealUniqueId").value(savedDeal.getDealUniqueId()));

        verify(fxDealService, times(1)).processFXDeal(any(FXDealEntity.class));
    }

    @Test
    void createFXDeals_ShouldReturnSavedDeals_WhenValidRequest() throws Exception {
        Path fxDealsPath = ResourceUtils.getFile("classpath:fxDeals.json").toPath();
        String fxDealsJson = new String(Files.readAllBytes(fxDealsPath));


        when(fxDealService.processFXDeal(any(FXDealEntity.class))).thenAnswer(i -> i.getArgument(0));


        mockMvc.perform(post("/api/fxdeals/batch")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(fxDealsJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }

}

