package com.progresssoft.fxdeals.controller;


import com.google.common.flogger.FluentLogger;
import com.progresssoft.fxdeals.domain.model.Deal;
import com.progresssoft.fxdeals.platform.controller.DealController;
import com.progresssoft.fxdeals.platform.spring.service.DealServiceImpl;
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
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(MockitoExtension.class)
class DealControllerTest {



    @Mock
    private DealServiceImpl dealService;

    private DealController dealController;

    private MockMvc mockMvc;


    @BeforeEach
    void setUp() {

        dealController = new DealController(dealService);
        mockMvc = MockMvcBuilders.standaloneSetup(dealController).build();
    }

    @Test
    void createFXDeal_ShouldReturnSavedDeal_WhenValidRequest() throws Exception {
        Path fxDealsPath = ResourceUtils.getFile("classpath:fxDeal.json").toPath();
        String fxDealsJson = new String(Files.readAllBytes(fxDealsPath));

        Deal savedDeal = Deal.builder().build();

        when(dealService.importDeal(any(Deal.class))).thenReturn(savedDeal);
        mockMvc.perform(post("/api/fxdeals")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(fxDealsJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(savedDeal.getId()));

        verify(dealService, times(1)).importDeal(any(Deal.class));
    }

    @Test
    void createFXDeals_ShouldReturnSavedDeals_WhenValidRequest() throws Exception {
        Path fxDealsPath = ResourceUtils.getFile("classpath:fxDeals.json").toPath();
        String fxDealsJson = new String(Files.readAllBytes(fxDealsPath));


        when(dealService.importDeal(any(Deal.class))).thenAnswer(i -> i.getArgument(0));

        mockMvc.perform(post("/api/fxdeals/batch")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(fxDealsJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }

}

